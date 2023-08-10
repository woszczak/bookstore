package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.edu.wszib.bookstore.dto.CartDTO;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.Product;

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

    @AfterMapping
    default void setQuantity(@MappingTarget ProductDTO productDTO, Product product) {
        if (product.getQuantity() < 0) {
            productDTO.setQuantity(1);
        }

    }
}
