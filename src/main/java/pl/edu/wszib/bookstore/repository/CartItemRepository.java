package pl.edu.wszib.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    CartItem findByCartAndProductId(Cart cart, Long productId);
}
