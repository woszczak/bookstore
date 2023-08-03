package pl.edu.wszib.bookstore.service;

import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Product getProduct(long id);
    //    List<Product> getProductByCategory(Category category);
    Product save(Product product);
    void delete(long id);
    List<Product> getProductsByCategory(Category category);
    List<Product> getBestsellers();


}
