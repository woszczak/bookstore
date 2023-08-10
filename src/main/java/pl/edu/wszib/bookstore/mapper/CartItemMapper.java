package pl.edu.wszib.bookstore.mapper;


import org.mapstruct.Mapper;
import pl.edu.wszib.bookstore.dto.CartItemDTO;
import pl.edu.wszib.bookstore.dto.CategoryDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.CartItem;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class})
public interface CartItemMapper {

    CartItem toDB(CartItemDTO cartItemDTO);
    CartItemDTO toDTO(CartItem cartItem);
    List<CartItem> toDB(List<CategoryDTO> cartItemDTOs);
    List<CartItemDTO> toDTO(List<CartItem> cartItems);


}
