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


@RestController
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;

    public AdminController(ProductService productService, ProductMapper productMapper, CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping("admin/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) throws IOException {

        ProductDTO savedProductDTO = productService.save(productDTO);
        return savedProductDTO;
    }


    @PutMapping("admin/edit/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("price") BigDecimal price, @RequestParam("categoryName") String categoryName, @RequestParam("bestseller") Boolean bestseller, @RequestParam("quantity") Integer quantity) throws IOException {
        ProductDTO updatedProductDTO = productService.edit(id, name, price, categoryName, bestseller, quantity);

        if (updatedProductDTO != null) {
            return ResponseEntity.ok(updatedProductDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("admin/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.delete(productId);
    }

}
