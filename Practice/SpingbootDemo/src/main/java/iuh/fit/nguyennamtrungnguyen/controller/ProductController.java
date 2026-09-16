package iuh.fit.nguyennamtrungnguyen.controller;

import iuh.fit.nguyennamtrungnguyen.model.Product;
import iuh.fit.nguyennamtrungnguyen.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nguyễn Nam Trung Nguyên
 * @version 1.0
 * @MSSV 23640731
 * @Class DHKTPM19ATT
 * @since 9/16/2026
 */

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // ===============================
    // LIST
    // GET /products
    // ===============================

    @GetMapping
    public String listProducts(Model model) {

        model.addAttribute(
                "products",
                productService.getAll()
        );

        return "list-products";
    }

    // ===============================
    // SHOW ADD FORM
    // GET /products/add
    // ===============================

    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        return "add-product";
    }

    // ===============================
    // ADD
    // POST /products/save
    // ===============================

    @PostMapping("/save")
    public String saveProduct(
            @ModelAttribute("product") Product product) {

        productService.save(product);

        return "redirect:/products";
    }

    // ===============================
    // SHOW EDIT FORM
    // GET /products/edit/{id}
    // ===============================

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Product product = productService.getById(id);

        model.addAttribute(
                "product",
                product
        );

        return "edit-product";
    }

    // ===============================
    // UPDATE
    // POST /products/update/{id}
    // ===============================

    @PostMapping("/update/{id}")
    public String updateProduct(
            @PathVariable Long id,
            @ModelAttribute("product") Product product) {

        productService.update(id, product);

        return "redirect:/products";
    }

    // ===============================
    // DELETE
    // GET /products/delete/{id}
    // ===============================

    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        productService.delete(id);

        return "redirect:/products";
    }
}