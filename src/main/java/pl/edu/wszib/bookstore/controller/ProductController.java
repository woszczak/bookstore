package pl.edu.wszib.bookstore.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
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
    public List<ProductDTO> getProducts() {
        List<Product> products = productService.getAllProducts();
        return productMapper.toDTOList(products);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        if (product != null) {
            ProductDTO productDTO = productMapper.toDTO(product);
            return ResponseEntity.ok(productDTO);

        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable("category") String categoryName) {
        Category category = Category.valueOf(categoryName.toUpperCase());

        List<Product> products = productService.getProductsByCategory(category);
        return productMapper.toDTOList(products);
    }


    @GetMapping("/bestsellers")
    public List<ProductDTO> getBestsellers() {
        List<Product> bestsellers = productService.getBestsellers();
        return productMapper.toDTOList(bestsellers);
    }




    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestParam("name") String name,
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

        Product savedProduct = productService.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") Long productId) {
        productService.delete(productId);
    }

@PutMapping("edit/{id}")
    public ResponseEntity<ProductDTO> editProduct (@PathVariable("id") Long id,
                                                   @RequestParam("name") String name,
                                                   @RequestParam("price") Double price,
                                                   @RequestParam("category") Category category,
                                                   @RequestParam("bestseller") Boolean bestseller,
                                                   @RequestParam(value = "picture", required = false) MultipartFile picture
                                                   ) throws IOException{
        Product product = productService.getProduct(id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setBestseller(bestseller);

        if (picture != null && !picture.isEmpty()){
            product.setPicture(picture.getBytes());
        }

        Product updatedProduct = productService.save(product);
        ProductDTO updatedProductDTO = productMapper.toDTO(updatedProduct);
        return ResponseEntity.ok(updatedProductDTO);

}


}
