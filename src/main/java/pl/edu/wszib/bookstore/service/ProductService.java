package pl.edu.wszib.bookstore.service;

import pl.edu.wszib.bookstore.dto.ProductDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProduct(Long id);
    ProductDTO save(ProductDTO productDTO) throws IOException;
    void delete(Long id);

    List<ProductDTO> getProductsByCategory(String categoryName);

    List<ProductDTO> searchProductByName(String keyword);

    List<ProductDTO> getBestsellers();
    ProductDTO edit(Long id, String name, BigDecimal price, String categoryName, Boolean bestseller, Integer quantity) throws IOException;


}