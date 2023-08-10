package pl.edu.wszib.bookstore.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private ProductService productService;
    private ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(value = {""})
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable("id") Long id) {
        return productService.getProduct(id);
    }


    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable("category") String categoryName) {
        Category category = Category.valueOf(categoryName.toUpperCase());
        return productService.getProductsByCategory(category);
    }


    @GetMapping("/bestsellers")
    public List<ProductDTO> getBestsellers() {
        return productService.getBestsellers();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) throws IOException {

        ProductDTO savedProductDTO = productService.save(productDTO);
        return savedProductDTO;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.delete(productId);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<ProductDTO> editProduct(@PathVariable("id") Long id,
                                                  @RequestParam("name") String name,
                                                  @RequestParam("price") BigDecimal price,
                                                  @RequestParam("category") Category category,
                                                  @RequestParam("bestseller") Boolean bestseller,
                                                  @RequestParam("quantity") Integer quantity,
                                                  @RequestParam(value = "picture", required = false) MultipartFile picture
    ) throws IOException {
        ProductDTO updatedProductDTO = productService.edit(id, name, price, category, bestseller, quantity, picture );

        if (updatedProductDTO != null) {
            return ResponseEntity.ok(updatedProductDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
