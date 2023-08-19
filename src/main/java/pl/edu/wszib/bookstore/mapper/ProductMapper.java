package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "description", target = "description")
    @Mapping(source = "id", target = "id")
    ProductDTO toDTO(Product product);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "bestseller", target = "bestseller")
    @Mapping(source = "description", target = "description")
    Product toDB(ProductDTO productDTO);

    List<ProductDTO> toDTOList(List<Product> products);
}
