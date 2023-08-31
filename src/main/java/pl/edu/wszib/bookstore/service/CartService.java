package pl.edu.wszib.bookstore.service;


import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.dto.CartStatusDTO;

import java.util.List;


public interface CartService {

    CartDTO add(Long productId, Integer quantity);

    CartDTO remove(Long productId, Integer quantity);

    CartDTO clear();
    CartDTO get();
    CartDTO get(Long id);
    CartDTO submitCart(Long cartId);
    List<CartDTO> getByStatus(CartStatusDTO statusDTO);



}
