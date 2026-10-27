package iuh.fit.eshop.controller;

import iuh.fit.eshop.service.CartService;
import iuh.fit.eshop.model.Product;
import iuh.fit.eshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    @GetMapping
    public String view(Model model) {
        model.addAttribute("cart", cartService);
        return "cart/view";
    }
    @PostMapping("/add")
    public String add(@RequestParam Long productId,
                      @RequestParam(defaultValue = "1") int quantity,
                      RedirectAttributes ra) {
        Product p = productService.findById(productId);
        int wanted = cartService.getQuantityOf(productId) + quantity;
        if (quantity < 1 || wanted > p.getQuantity()) {
            ra.addFlashAttribute("error", "Không đủ hàng: \"" + p.getName() + "\" chỉ còn " +
                    p.getQuantity());
            return "redirect:/products/" + productId;
        }
        cartService.add(p, quantity);
        ra.addFlashAttribute("message", "Đã thêm \"" + p.getName() + "\" vào giỏ hàng");
        return "redirect:/cart";
    }
    @PostMapping("/update")
    public String update(@RequestParam Long productId, @RequestParam int quantity,
                         RedirectAttributes ra) {
        Product p = productService.findById(productId);
        if (quantity > p.getQuantity()) {
            ra.addFlashAttribute("error", "\"" + p.getName() + "\" chỉ còn " + p.getQuantity());
        } else {
            cartService.update(productId, quantity);
        }
        return "redirect:/cart";
    }
    @PostMapping("/remove/{productId}")
    public String remove(@PathVariable Long productId) {
        cartService.remove(productId);
        return "redirect:/cart";
    }
    @PostMapping("/clear")
    public String clear() {
        cartService.clear();
        return "redirect:/cart";
    }
}