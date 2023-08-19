package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CartItemMapper.class,
                CartStatusMapper.class}
)
public interface CartMapper {
    CartDTO toDTO(Cart cart);

    Cart toDB(CartDTO cartDTO);

    List<CartDTO> toDTO(List<Cart> carts);

    List<Cart> toDB(List<CartDTO> cartDTOS);


    @AfterMapping
    default void calculateTotalPrice(@MappingTarget CartDTO cartDTO, Cart cart) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cart.getItems()) {
            BigDecimal itemPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemPrice);
        }
        cartDTO.setTotalPrice(total);
    }
}
