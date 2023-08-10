package pl.edu.wszib.bookstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.CartMapper;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.service.CartService;
import pl.edu.wszib.bookstore.service.ProductService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private CartMapper cartMapper;


    public CartController(CartService cartService, ProductService productService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartMapper = cartMapper;
    }


    @PutMapping("/add/{productId}")
    public CartDTO add(@PathVariable Long productId, @RequestParam(required = false, defaultValue = "1") Integer quantity) {
        return cartService.add(productId, quantity);
    }


    @GetMapping
    public CartDTO get() {
        return cartService.get();
    }

    @GetMapping("/{id}")
    private CartDTO get(@PathVariable Long id) {
        return cartService.get(id);
    }



@PutMapping("remove/{productId}")
    private CartDTO remove(@PathVariable Long productId, @RequestParam(required = false, defaultValue = "1") Integer quantity){
        return cartService.remove(productId, quantity);
}

@PostMapping("/clear")
    public CartDTO clear(){
        return cartService.clear();
}

}




