package pl.edu.wszib.bookstore.service;


import pl.edu.wszib.bookstore.dto.CartDTO;


public interface CartService {

    CartDTO add(Long productId, Integer quantity);

    CartDTO remove(Long productId, Integer quantity);

    CartDTO submit(String email);

    CartDTO clear();
    CartDTO get();
    CartDTO get(Long id);

}
