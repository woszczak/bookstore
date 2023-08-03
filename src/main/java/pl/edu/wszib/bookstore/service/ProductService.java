package pl.edu.wszib.bookstore.service;

import pl.edu.wszib.bookstore.model.Product;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Product getProduct(long id);
    //    List<Product> getProductByCategory(Category category);
    Product save(Product product);
    void delete(long id);


}
