
package pl.edu.wszib.bookstore.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.Cart;
import pl.edu.wszib.bookstore.entity.CartItem;
import pl.edu.wszib.bookstore.model.CartModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartMapper {

    public static Cart toEntity(pl.edu.wszib.bookstore.model.CartModel cartModel) {
        Cart cart = new Cart();
        cart.setId(cartModel.getId());
        cart.setStatus(CartStatusMapper.toEntity(cartModel.getCartStatusModel()));

        if (cartModel.getItems() != null) {
            cart.setItems(cartModel.getItems().stream()
                    .map(CartItemMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        BigDecimal totalPrice = calculateTotalPrice(cart.getItems());
        cartModel.setTotalPrice(totalPrice);

        return cart;
    }

    public static CartModel toModel(Cart cart) {
        CartModel cartModel = new CartModel();
        cartModel.setId(cart.getId());
        cartModel.setCartStatusModel(CartStatusMapper.toModel(cart.getStatus()));

        if (cart.getItems() != null) {
            cartModel.setItems(cart.getItems().stream()
                    .map(CartItemMapper::toModel)
                    .collect(Collectors.toList()));
        }

        BigDecimal totalPrice = calculateTotalPrice(cart.getItems());
        cartModel.setTotalPrice(totalPrice);

        return cartModel;
    }

    private static BigDecimal calculateTotalPrice(List<CartItem> cartItemEntities) {
        BigDecimal total = BigDecimal.ZERO;
        if (cartItemEntities != null) {
            for (CartItem cartItem : cartItemEntities) {
                BigDecimal itemPrice = cartItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
                total = total.add(itemPrice);
            }
        }
        return total;
    }
}
