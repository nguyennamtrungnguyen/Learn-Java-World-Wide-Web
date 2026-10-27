package iuh.fit.eshop.controller;

import iuh.fit.eshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
@ControllerAdvice(basePackages = "com.example.eshop.controller")
@RequiredArgsConstructor
public class GlobalModelAttributes {
    private final CartService cartService;
    @ModelAttribute("cartCount")
    public int cartCount() {
        return cartService.getTotalQuantity();
    }
}