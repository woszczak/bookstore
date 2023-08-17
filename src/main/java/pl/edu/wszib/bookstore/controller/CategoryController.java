package pl.edu.wszib.bookstore.controller;

import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.dto.CategoryDTO;
import pl.edu.wszib.bookstore.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public List<CategoryDTO> list() {
        return categoryService.list();
    }


    @GetMapping("/{id}")
    public CategoryDTO get(@PathVariable Long id){
        return categoryService.get(id);
    }


    @PostMapping("/createCategory")
    public CategoryDTO create(@RequestBody CategoryDTO categoryDTO){
        return categoryService.create(categoryDTO);
    }


    @PostMapping("/updateCategory")
    public CategoryDTO update(@RequestBody CategoryDTO categoryDTO){
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
