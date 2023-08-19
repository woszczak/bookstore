package pl.edu.wszib.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);

    List<Product> findByBestseller(boolean bestseller);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
     List<Product> searchProductByName(String keyword);

}
