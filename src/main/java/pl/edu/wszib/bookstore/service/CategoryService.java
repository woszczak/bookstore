package pl.edu.wszib.bookstore.service;

import pl.edu.wszib.bookstore.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {


    List<CategoryDTO> list();
    CategoryDTO get(Long id);

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(CategoryDTO categoryDTO);

    void delete(Long id);

}
