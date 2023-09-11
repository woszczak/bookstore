package pl.edu.wszib.bookstore.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.entity.Cart;
import pl.edu.wszib.bookstore.entity.CartItem;
import pl.edu.wszib.bookstore.entity.CartStatus;
import pl.edu.wszib.bookstore.entity.Product;
import pl.edu.wszib.bookstore.mapper.CartItemMapper;
import pl.edu.wszib.bookstore.model.CartItemModel;
import pl.edu.wszib.bookstore.model.CartModel;
import pl.edu.wszib.bookstore.model.ProductModel;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.CartMapper;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.repository.CartItemRepository;
import pl.edu.wszib.bookstore.repository.CartRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {


    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private CartMapper cartMapper;
    private CartItemMapper cartItemMapper;
    private ProductMapper productMapper;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper cartMapper, CartItemMapper cartItemMapper, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.productMapper = productMapper;
    }

    public CartService() {
    }

    @Transactional
    public CartItemModel add(CartItemModel cartItemModel, Long productId, Integer quantity) {
        Cart cart = getOrCreate();
        Product product = getProduct(productId);
        CartItem cartItem = cartItemRepository.findByCartAndProductId(cart, productId);

        if (cartItem != null) {
            int newQuantity = cartItem.getQuantity() + quantity;
            if (product.getQuantity() < newQuantity) {
                return null;
            }
            cartItem.setQuantity(newQuantity);
        } else {
            if (product.getQuantity() < quantity) {
                return null;
            }

            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        }

        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return cartItemMapper.toModel(savedCartItem);
    }


    public CartModel get(Long id) {
        return cartRepository.findById(id)
                .map(CartMapper::toModel)
                .orElseThrow(() -> new NotFound(id, Cart.class));
    }

    @Transactional
    public CartModel get() {
        Cart cart = getOrCreate();
        List<CartItemModel> cartItemModels = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            ProductModel productModel = productMapper.toModel(cartItem.getProduct());
            CartItemModel cartItemModel = new CartItemModel(
                    cartItem.getId(),
                    productModel,
                    cartItem.getQuantity(),
                    cartItem.getCart(),
                    cartItem.getCreateDate(),
                    cartItem.getUpdateDate()
            );
            cartItemModels.add(cartItemModel);
        }

        CartModel cartModel = cartMapper.toModel(cart);
        cartModel.setItems(cartItemModels);

        return cartModel;
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFound(id, Product.class));
    }

    public Cart getOrCreate() {
        List<Cart> carts = cartRepository.findByStatus(CartStatus.NEW);

        if (carts.isEmpty()) {
            return create();
        } else {
            return carts.get(0);
        }
    }
    private Cart create() {
        Cart newCart = new Cart();
        newCart.setStatus(CartStatus.NEW);
        return cartRepository.save(newCart);
    }

    @Transactional
    public void remove(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new NotFound(cartItemId, CartItem.class));

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
        } else if (cartItem.getQuantity() <= quantity) {
            cartItemRepository.delete(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
            cartItemRepository.save(cartItem);
        }
    }

    @Transactional
    public CartModel submit() {
        Cart cart = getOrCreate();
        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cart.setStatus(CartStatus.SUBMITTED);
        return cartMapper.toModel(cartRepository.save(cart));
    }
}


