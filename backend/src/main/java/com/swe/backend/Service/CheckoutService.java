package com.swe.backend.Service;

import com.swe.backend.DTOs.CheckoutRequestDto;
import com.swe.backend.DTOs.OrderDto;
import com.swe.backend.Entity.*;
import com.swe.backend.Exceptions.OutOfStockException;
import com.swe.backend.Mappers.CartItemMapper;
import com.swe.backend.Mappers.CartToOrderItemMapper;
import com.swe.backend.Mappers.OrderMapper;
import com.swe.backend.Repository.*;
import com.swe.backend.Util.PaymentStatusENUM;

import java.math.BigDecimal;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service that converts the current user's active cart into an Order & Payment atomically.
 */
@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;
    private final PaymentRepository paymentRepo;
    private final OrderMapper orderMapper;
    private final CartToOrderItemMapper cartToOrderItemMapper;
    private UserRepository userRepo;

    /**
     * Perform checkout as a single ACID transaction. If any pre‑condition fails
     * (e.g., insufficient inventory), an exception is thrown and the entire
     * transaction rolls back.
     */
    @Transactional(rollbackOn = Exception.class)
    public OrderDto processCheckout(CheckoutRequestDto checkoutRequestDto) {
        User user = currentUser();

        Cart cart = cartRepo.findByUserAndIsActive(user, true)
                .orElseThrow(() -> new IllegalStateException("No active cart"));

        if (cart.getCartItems().isEmpty())
            throw new IllegalStateException("Cart is empty");
        // 1. Verify & reserve inventory ------------------------------------
        for (CartItem cartItem : cart.getCartItems()) {
            Product p = cartItem.getProduct();
            if (p.getInventoryQty() < cartItem.getQuantity())
                throw new OutOfStockException(p.getName());
            p.setInventoryQty(p.getInventoryQty() - cartItem.getQuantity());
            productRepo.save(p); // write‑back new stock level
        }

        // 2. Create Order + OrderItems -------------------------------------
        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus("PENDING");
        order.setOrderDate(Instant.now());
        order.setShippingAddress(checkoutRequestDto.getShippingAddress());

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartToOrderItemMapper::toOrderItem)
                .toList();
        order.setOrderItems(orderItems);

        BigDecimal subtotal = orderItems.stream()
                .map(oi -> oi.getPricePerUnit().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalCost(subtotal); // taxes/discounts omitted for MVP

        orderRepo.save(order);

        // 3. Create Payment --------------------------------------------------
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setPaymentMethod(checkoutRequestDto.getPaymentMethod());
        payment.setPaymentStatus("PENDING");
        payment.setSubtotal(subtotal);
        paymentRepo.save(payment);

        // 4. Deactivate cart -----------------------------------------------
        cart.setIsActive(false);
        cartRepo.save(cart);

        return orderMapper.toOrderDto(order);
    }

    // ---------------------------------------------------------------------

    private User currentUser() {
        UserDetails principal =
                (UserDetails) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getPrincipal();
        return userRepo.findByUsername(principal.getUsername()).orElseThrow();
    }
}