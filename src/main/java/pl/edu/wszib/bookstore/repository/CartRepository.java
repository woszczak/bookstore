package pl.edu.wszib.bookstore.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bookstore.entity.Cart;
import pl.edu.wszib.bookstore.entity.CartStatus;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long > {

    List<Cart> findByStatus(CartStatus status);

}


