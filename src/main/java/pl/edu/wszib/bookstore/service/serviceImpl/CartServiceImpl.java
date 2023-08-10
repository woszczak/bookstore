package pl.edu.wszib.bookstore.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.mapper.CartMapper;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.CartItem;
import pl.edu.wszib.bookstore.model.CartStatus;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.repository.CartItemRepository;
import pl.edu.wszib.bookstore.repository.CartRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;
import pl.edu.wszib.bookstore.service.CartService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {


    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private CartMapper mapper;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper mapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CartDTO add(Long productId, Integer quantity) {
        Cart cart = getOrCreate();
        Product product = getProduct(productId);
        CartItem cartItem =  cartItemRepository.findByCartAndProductId(cart, productId);
        boolean newlyAdded = false;
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            newlyAdded = true;
        }
        if (product.getQuantity() < cartItem.getQuantity()) {
            return null;
        }

        return mapper.toDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO remove(Long productId, Integer quantity) {

        Cart cart = getOrCreate();
        Product product = getProduct(productId);
        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId);
        if(cartItem == null){
            return null;
        }
        cartItem.setQuantity((cartItem.getQuantity()) - quantity);

        if (cartItem.getQuantity() <= 0){
            cart.getItems().remove(cartItem);
        }else {
            cartItemRepository.save(cartItem);
        }

        return mapper.toDTO(cartRepository.save(cart));
    }

    @Override
    public CartDTO submit(String email) {
        return null;
    }

    @Transactional
    @Override
    public CartDTO clear() {
        Cart cart = getOrCreate();
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        return mapper.toDTO(cartRepository.save(cart));
    }

    @Override
    @Transactional
    public CartDTO get() {
        return mapper.toDTO(getOrCreate());
    }

    @Override
    public CartDTO get(Long id) {
        return cartRepository
                .findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(null);
    }


    public Cart getOrCreate() {
        return cartRepository.findByStatus(CartStatus.NEW)
                .orElseGet(this::create);
    }


    public Cart create() {
        Cart cart = new Cart();
        cart.setStatus(CartStatus.NEW);
        return cartRepository.save(cart);
    }


}
