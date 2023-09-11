package pl.edu.wszib.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.entity.Cart;
import pl.edu.wszib.bookstore.model.CategoryModel;
import pl.edu.wszib.bookstore.model.ProductModel;
import pl.edu.wszib.bookstore.service.CategoryService;
import pl.edu.wszib.bookstore.service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String getAllProducts(Model model) {
        Cart cart = new Cart();
        cart.setId((long) 1);
        model.addAttribute("cart", cart);
        List<ProductModel> products = productService.findAll();
        model.addAttribute("products", products);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }

    @GetMapping("/{product-id}")
    public String getProduct(@PathVariable("product-id") Long id, Model model) {
        ProductModel product = productService.getById(id);
        model.addAttribute("product", product);
        return "productPage";
    }

    @GetMapping("/category/{categoryName}")
    public String getProductsByCategory(@PathVariable("categoryName") String categoryName, Model model) {
        List<ProductModel> products = productService.getProductsByCategory(categoryName);
        model.addAttribute("products", products);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }

    @GetMapping("/search")
    public String searchProductByName(@RequestParam String keyword, Model model) {
        List<ProductModel> products = productService.searchProductByName(keyword);
        model.addAttribute("products", products);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }

    @GetMapping("/bestsellers")
    public String getBestsellers(Model model) {
        List<ProductModel> bestsellers = productService.getBestsellers();
        model.addAttribute("products", bestsellers);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }


    @GetMapping("/sortedByPriceAsc")
    public String getAllProductsSortedByPriceAsc(Model model) {
        List<ProductModel> products = productService.getAllProductsSortedByPriceAsc();
        model.addAttribute("products", products);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }

    @GetMapping("/sortedByPriceDesc")
    public String getAllProductsSortedByPriceDesc(Model model) {
        List<ProductModel> products = productService.getAllProductsSortedByPriceDesc();
        model.addAttribute("products", products);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "homePage";
    }
}
