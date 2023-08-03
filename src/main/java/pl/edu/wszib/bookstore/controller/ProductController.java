package pl.edu.wszib.bookstore.controller;


import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {""})
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{category}")
    public List<Product> getProductsByCategory(@PathVariable("category") String categoryName) {
        Category category = Category.valueOf(categoryName.toUpperCase());
        return productService.getProductsByCategory(category);
    }


    @GetMapping("/bestsellers")
    public List<Product> getBestsellers() {
        return productService.getBestsellers();
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestParam("name") String name,
                              @RequestParam("price") Double price,
                              @RequestParam("category") Category category,
                              @RequestParam("bestseller") Boolean bestseller,
                              @RequestParam(value = "picture", required = false) MultipartFile picture) throws IOException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setBestseller(bestseller);

        if (picture != null && !picture.isEmpty()) {
            product.setPicture(picture.getBytes());
        }
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.delete(productId);
    }

}
