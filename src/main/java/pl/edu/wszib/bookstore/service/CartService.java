package pl.edu.wszib.bookstore.service;


import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;


public interface CartService {

    CartDTO add(Long productId, Integer quantity);

    CartDTO remove(Long productId, Integer quantity);

    CartDTO submit(String email);

    CartDTO clear();
    CartDTO get();
    CartDTO get(Long id);

}
