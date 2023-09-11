package pl.edu.wszib.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.model.CategoryModel;
import pl.edu.wszib.bookstore.model.ProductModel;
import pl.edu.wszib.bookstore.mapper.ProductMapper;

import pl.edu.wszib.bookstore.service.CategoryService;
import pl.edu.wszib.bookstore.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;

    public AdminController(ProductService productService, ProductMapper productMapper, CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAllProducts(Model model) {
        List<ProductModel> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("newProducts", new ProductModel());
        return "adminPage";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute("newProduct") ProductModel productModel, Model model) {
        productService.add(productModel);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new ProductModel());
        return "redirect:/admin";
    }


    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("newProduct", new ProductModel());
        return "adminAddPage";
    }

    @GetMapping("/edit")
    public String editProductsPage(Model model) {
        List<ProductModel> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("newProducts", new ProductModel());
        return "adminEditPage";
    }

    @GetMapping("edit/{product-id}")
    public String editProduct(@PathVariable("product-id") Long id, Model model) {
        ProductModel product = productService.getById(id);
        model.addAttribute("product", product);

        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        return "adminEditFormPage";
    }


    @PutMapping("/edit/{product-id}")
    public String editProduct(
            @PathVariable("product-id") Long id,
            @ModelAttribute("updatedProduct") ProductModel updatedProduct) {
        ProductModel existingProduct = productService.getById(id);

        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setBestseller(updatedProduct.isBestseller());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            productService.edit(id, updatedProduct);
            return "redirect:/admin/edit";
        } else {
            return "redirect:/admin";
        }
    }

    @DeleteMapping("/delete/{product-id}")
    public String deleteProduct(@PathVariable("product-id") Long productId, Model model) {
        productService.delete(productId);
        return "redirect:/admin/edit";
    }

//    =========zarzadzanie kategoriami===================
    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("newCategory") CategoryModel categoryModel, Model model) {
        categoryService.addCategory(categoryModel);
        return "redirect:/admin";
    }

    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categories", categoryService.list());
        model.addAttribute("newCategory", new CategoryModel());
        return "adminAddCategoryPage";
    }

    @GetMapping("/editCategory")
    public String editCategoryPage(Model model) {
        List<CategoryModel> categories = categoryService.list();
        model.addAttribute("categories", categories);
        model.addAttribute("newCategories", new CategoryModel());
        return "adminEditCategoryPage";
    }

    @GetMapping("editCategory/{category-id}")
    public String editCategory(@PathVariable("category-id") Long id, Model model) {
        CategoryModel categoryModel = categoryService.get(id);
        model.addAttribute("category", categoryModel);
        return "adminEditCategoryFormPage";
    }

    @PutMapping("/editCategory/{category-id}")
    public String editCategory(
            @PathVariable("category-id") Long id,
            @ModelAttribute("updatedCategory") CategoryModel updatedCategoryModel) {
        CategoryModel existingCategoryModel = categoryService.get(id);

        if (existingCategoryModel != null) {

            existingCategoryModel.setName(updatedCategoryModel.getName());
            categoryService.editCategory(id, updatedCategoryModel);

            return "redirect:/admin/editCategory";
        } else {
            return "redirect:/admin/editCategory";
        }
    }

    @DeleteMapping("/deleteCategory/{category-id}")
    public String deleteCategory(@PathVariable("category-id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/admin/editCategory";
    }
}
