package pl.edu.wszib.bookstore.service;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.CartItem;
import pl.edu.wszib.bookstore.mapper.CartItemMapper;
import pl.edu.wszib.bookstore.model.CartItemModel;
import pl.edu.wszib.bookstore.repository.CartItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartItemsService {

    private CartItemRepository cartItemRepository;
    private CartItemMapper cartItemMapper;

    public CartItemsService(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    public List<CartItemModel> list() {
        return cartItemRepository.findAll().stream()
                .map(CartItemMapper::toModel)
                .collect(Collectors.toList());
    }

    public CartItemModel add(CartItemModel cartItemModel) {
        CartItem newCartItem = cartItemMapper.toEntity(cartItemModel);
        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        return cartItemMapper.toModel(savedCartItem);
    }

}
