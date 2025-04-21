package com.swe.backend.service;

import com.swe.backend.dtos.CheckoutRequestDto;
import com.swe.backend.dtos.OrderDto;
import com.swe.backend.entity.*;
import com.swe.backend.exceptions.OutOfStockException;
import com.swe.backend.mappers.CartToOrderItemMapper;
import com.swe.backend.mappers.OrderMapper;
import com.swe.backend.repository.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private final UserRepository userRepo;

    /**
     * Perform checkout as a single ACID transaction. If any pre‑condition fails
     * (e.g., insufficient inventory), an exception is thrown and the entire
     * transaction rolls back.
     */
    @Transactional
    public OrderDto processCheckout(CheckoutRequestDto checkoutRequestDto) {
        User user = currentUser();

        Cart cart = cartRepo.findByUserAndIsActive(user, true)
                .orElseThrow(() -> new IllegalStateException("No active cart"));

        if (cart.getCartItems().isEmpty())
            throw new IllegalStateException("Cart is empty");
        // Verify & reserve inventory
        for (CartItem cartItem : cart.getCartItems()) {
            Product p = cartItem.getProduct();
            if (p.getInventoryQty() < cartItem.getQuantity())
                throw new OutOfStockException(p.getName());
            p.setInventoryQty(p.getInventoryQty() - cartItem.getQuantity());
            productRepo.save(p); // write‑back new stock level
        }

        // Create Order + OrderItems
        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(checkoutRequestDto.getShippingAddress());
        order.setDiscountPromotion(BigDecimal.ZERO);
        order.setOrderDate(Instant.now());
        order.setOrderStatus("PENDING");

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getCartItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);

            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPricePerUnit(cartItem.getProduct().getPrice());

            OrderItemId id = new OrderItemId();
            // ❌ DON'T call order.getId() here unless order is already saved
            id.setProductId(cartItem.getProduct().getId());
            item.setId(id);

            orderItems.add(item);
        }


        BigDecimal subtotal = orderItems.stream()
                .map(oi -> oi.getPricePerUnit().multiply(BigDecimal.valueOf(oi.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal tax = subtotal.setScale(2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(0.05));


        // Process payment
        Payment payment = new Payment();
        payment.setPaymentMethod(checkoutRequestDto.getPaymentMethod());
        payment.setPaymentStatus("PAID");
        payment.setSubtotal(subtotal);
        payment.setTaxTotal(tax);
        payment.setPaymentDate(Instant.now());
        paymentRepo.save(payment);


        order.setTransaction(payment);
        order.setOrderItems(orderItems);
        order.setTotalCost(subtotal.add(tax)); // taxes/discounts omitted for MVP
        order.setOrderItems(orderItems);
        orderRepo.save(order);


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