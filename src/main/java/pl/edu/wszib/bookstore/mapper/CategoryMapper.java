package pl.edu.wszib.bookstore.mapper;

import org.mapstruct.Mapper;
import pl.edu.wszib.bookstore.dto.CategoryDTO;
import pl.edu.wszib.bookstore.model.Category;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface CategoryMapper {

    Category toDB(CategoryDTO categoryDTO);
    CategoryDTO toDTO(Category category);
    List<Category> toDB(List<CategoryDTO> categoryDTOs);
    List<CategoryDTO> toDTO(List<Category> category);


}
