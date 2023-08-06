package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.model.Product;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    ProductDTO toDTO(Product product);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "bestseller", constant = "false")
    Product fromDTO(ProductDTO productDTO);


    List<ProductDTO> toDTOList(List<Product> products);
}
