package pl.edu.wszib.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bookstore.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
