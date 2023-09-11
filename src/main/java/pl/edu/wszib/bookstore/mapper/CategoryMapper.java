package pl.edu.wszib.bookstore.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.Category;
import pl.edu.wszib.bookstore.model.CategoryModel;

@Component
public class CategoryMapper {


    public static Category toEntity(CategoryModel categoryModel) {

        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setName(categoryModel.getName());
        category.setCreateDate(categoryModel.getCreateDate());
        category.setUpdateDate(categoryModel.getUpdateDate());
        return category;
    }

    public static CategoryModel toModel(Category category) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        categoryModel.setCreateDate(category.getCreateDate());
        categoryModel.setUpdateDate(category.getUpdateDate());
        return categoryModel;
    }
}
