package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.bookstore.dto.CartStatusDTO;
import pl.edu.wszib.bookstore.model.CartStatus;

@Mapper(
        componentModel = "spring"
)
public interface CartStatusMapper {
    CartStatusDTO toDTO(CartStatus cartStatus);
    CartStatus toDB(CartStatusDTO cartStatusDTO);
}
