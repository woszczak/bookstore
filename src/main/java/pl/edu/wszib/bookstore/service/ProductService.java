package pl.edu.wszib.bookstore.service;

import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProduct(long id);
    Product save(Product product);
//    Product updateProduct (long id, String name, double price, Category category, boolean bestseller);
    void delete(long id);
    List<Product> getProductsByCategory(Category category);
    List<Product> getBestsellers();


}