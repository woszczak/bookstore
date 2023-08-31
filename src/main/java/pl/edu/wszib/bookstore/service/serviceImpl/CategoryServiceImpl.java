package pl.edu.wszib.bookstore.service.serviceImpl;


import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.dto.CategoryDTO;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.CategoryMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.repository.CategoryRepository;
import pl.edu.wszib.bookstore.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper mapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CategoryDTO> list() {
        return mapper.toDTO(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO get(Long id) {
        return categoryRepository.findById(id).map(mapper::toDTO).
        orElseThrow(() -> new NotFound(id, Category.class));

    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {

        categoryDTO.setId(null);
        Category toBeCreated = mapper.toDB(categoryDTO);
        return mapper.toDTO(categoryRepository.save(toBeCreated));
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO) {
        Category existing = categoryRepository.findById(categoryDTO.getId())
                .orElseThrow(() -> new NotFound(categoryDTO.getId(), Category.class));

        Category incoming = mapper.toDB(categoryDTO);
        existing.setName(incoming.getName());
        return mapper.toDTO(categoryRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Category exising = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFound(id, Category.class));
        categoryRepository.delete(exising);
    }

}