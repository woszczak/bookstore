package pl.edu.wszib.bookstore.service;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.entity.Category;
import pl.edu.wszib.bookstore.entity.Product;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.CategoryMapper;
import pl.edu.wszib.bookstore.model.CategoryModel;
import pl.edu.wszib.bookstore.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryModel> list() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toModel)
                .collect(Collectors.toList());
    }

    public CategoryModel get(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toModel)
                .orElseThrow(() -> new NotFound(id, Category.class));
    }

    public CategoryModel addCategory(CategoryModel categoryModel) {
        Category newCategory = categoryMapper.toEntity(categoryModel);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.toModel(savedCategory);
    }


    public CategoryModel editCategory(Long id, CategoryModel updatedCategoryModelModel) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound(id, Product.class));

        if (category != null) {
            category.setName(updatedCategoryModelModel.getName());
            Category updatedCategory = categoryRepository.save(category);

            return CategoryMapper.toModel(updatedCategory);
        } else {
            return null;
        }
    }
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
