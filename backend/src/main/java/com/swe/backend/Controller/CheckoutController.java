package com.swe.backend.Controller;

import com.swe.backend.DTOs.CheckoutRequestDto;
import com.swe.backend.DTOs.OrderDto;
import com.swe.backend.Service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    /** Creates an Order + Payment from the current user’s active cart. */
    @PostMapping
    public ResponseEntity<OrderDto> checkout(@Valid @RequestBody CheckoutRequestDto body) {
        OrderDto order = checkoutService.processCheckout(body);
        return ResponseEntity.ok(order);
    }
}
