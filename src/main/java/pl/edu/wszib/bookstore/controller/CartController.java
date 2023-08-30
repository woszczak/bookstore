package pl.edu.wszib.bookstore.controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.dto.CartStatusDTO;
import pl.edu.wszib.bookstore.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
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
    private CartDTO remove(@PathVariable Long productId,
                           @RequestParam(required = false, defaultValue = "1") Integer quantity) {
        return cartService.remove(productId, quantity);
    }

    @PostMapping("/clear")
    public CartDTO clear() {
        return cartService.clear();
    }


    @GetMapping("/submitted")
    public List<CartDTO> getSubmittedCarts() {
        return cartService.getByStatus(CartStatusDTO.SUBMITTED);
    }


    @PostMapping("/{cartId}/submit")
    public CartDTO submitCart(@PathVariable Long cartId) {
        return cartService.submitCart(cartId);
    }

}



