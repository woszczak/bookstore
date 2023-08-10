package pl.edu.wszib.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.CartStatus;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long > {

    Optional<Cart> findByStatus(CartStatus status);

}


