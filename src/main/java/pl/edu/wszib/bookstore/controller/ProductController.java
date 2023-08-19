package pl.edu.wszib.bookstore.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.service.CategoryService;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;

    public ProductController(ProductService productService, ProductMapper productMapper, CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @GetMapping(value = {"/products"})
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }


    @GetMapping("/category/{categoryName}/products")

    public List<ProductDTO> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String keyword) {
        List<ProductDTO> productDTOS = productService.searchProductByName(keyword);

        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("products/bestsellers")
    public List<ProductDTO> getBestsellers() {
        return productService.getBestsellers();
    }

    @GetMapping("/products/searchByPrice")
    public ResponseEntity<List<ProductDTO>> searchProductsByPriceRange(
            @RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        List<ProductDTO> productDTOS = productService.searchProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(productDTOS);
    }

    @GetMapping("/products/sortedByPriceAsc")
    public List<ProductDTO> getAllProductsSortedByPriceAsc() {
        return productService.getAllProductsSortedByPriceAsc();
    }

    @GetMapping("/products/sortedByPriceDesc")
    public List<ProductDTO> getAllProductsSortedByPriceDesc() {
        return productService.getAllProductsSortedByPriceDesc();
    }
}
