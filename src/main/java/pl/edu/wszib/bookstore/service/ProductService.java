package pl.edu.wszib.bookstore.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.model.Category;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProduct(Long id);
    ProductDTO save(ProductDTO productDTO);
    void delete(Long id);
    List<ProductDTO> getProductsByCategory(Category category);
    List<ProductDTO> getBestsellers();
    ProductDTO edit(Long id, String name, BigDecimal price, Category category, Boolean bestseller, Integer quantity, MultipartFile picture) throws IOException;


}