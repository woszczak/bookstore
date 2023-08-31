package pl.edu.wszib.bookstore.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.dto.CartItemDTO;
import pl.edu.wszib.bookstore.dto.CartStatusDTO;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.CartMapper;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.CartItem;
import pl.edu.wszib.bookstore.model.CartStatus;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.repository.CartItemRepository;
import pl.edu.wszib.bookstore.repository.CartRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;
import pl.edu.wszib.bookstore.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {


    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private CartMapper mapper;
    private ProductMapper productMapper;


    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductRepository productRepository, CartMapper mapper, ProductMapper productMapper) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.productMapper = productMapper;
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

        if (newlyAdded){
            cartItemRepository.save(cartItem);
        }

        cart = cartRepository.save(cart);
        return mapper.toDTO(cart);
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

            cartItemRepository.delete(cartItem);

        }else {
            cartItemRepository.save(cartItem);
        }

        return mapper.toDTO(cartRepository.save(cart));
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
    public CartDTO get(Long id) {
        return cartRepository
                .findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new NotFound(id, Cart.class));

    }



    @Override
    @Transactional
    public CartDTO get() {
        Cart cart = getOrCreate();
        List<CartItemDTO> cartItemDTOs = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            ProductDTO productDTO = productMapper.toDTO(cartItem.getProduct());
            CartItemDTO cartItemDTO = new CartItemDTO(
                    cartItem.getId(),
                    productDTO,
                    cartItem.getQuantity(),
                    cartItem.getCart(),
                    cartItem.getCreateDate(),
                    cartItem.getUpdateDate()
            );
            cartItemDTOs.add(cartItemDTO);
        }

        CartDTO cartDTO = mapper.toDTO(cart);
        cartDTO.setItems(cartItemDTOs);

        return cartDTO;
    }

    private Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFound(id, Product.class));

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


    @Override
    public CartDTO submitCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElse(null);

        if (cart != cartRepository.findById(cartId).orElse(null)) ;

        if (cart != null && cart.getStatus() == CartStatus.NEW) {
            cart.setStatus(CartStatus.SUBMITTED);
            cartRepository.save(cart);
            return mapper.toDTO(cart);
        }

        return null;
    }


    @Override
    public List<CartDTO> getByStatus(CartStatusDTO statusDTO) {
        CartStatus cartStatus = CartStatus.valueOf(statusDTO.name());
        Optional<Cart> carts = cartRepository.findByStatus(cartStatus);
        return carts.stream()
                .map(mapper::toDTO).collect(Collectors.toList());
    }
}
