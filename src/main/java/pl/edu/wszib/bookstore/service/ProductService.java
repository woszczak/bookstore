package pl.edu.wszib.bookstore.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.entity.Category;
import pl.edu.wszib.bookstore.entity.Product;
import pl.edu.wszib.bookstore.model.ProductModel;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.repository.CategoryRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {


    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductMapper productMapper;


    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<ProductModel> findAll(){
        List<Product> entities = productRepository.findAll();
        return entities.stream()
                .map(ProductMapper::toModel)
                .toList();
    }

    public ProductModel getById(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
        return ProductMapper.toModel(product);
    }

    public ProductModel add(ProductModel productModel) {
        Product newProduct = productMapper.toEntity(productModel);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toModel(savedProduct);
    }
    public ProductModel edit(Long id, ProductModel updatedProductModel) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFound(id, Product.class));

        if (product != null) {
            product.setName(updatedProductModel.getName());
            product.setDescription(updatedProductModel.getDescription());
            product.setPrice(updatedProductModel.getPrice());
            product.setQuantity(updatedProductModel.getQuantity());
            product.setBestseller(updatedProductModel.isBestseller());

            Product updatedProduct = productRepository.save(product);
            return ProductMapper.toModel(updatedProduct);
        } else {
            return null;
        }
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductModel> searchProductByName(String keyword) {
        List<Product> productEntities = productRepository.searchProductByName(keyword);
        return productEntities.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());

    }
    public List<ProductModel> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category != null) {
            List<Product> productEntities = productRepository.findByCategory(category);
            return productEntities.stream()
                    .map(ProductMapper::toModel)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
    public List<ProductModel> getAllProductsSortedByPriceAsc() {
        List<Product> productEntities = productRepository.findAllByOrderByPriceAsc();
        return productEntities.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getAllProductsSortedByPriceDesc() {
        List<Product> productEntities = productRepository.findAllByOrderByPriceDesc();
        return productEntities.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    public List<ProductModel> getBestsellers() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream()
                .filter(Product::isBestseller)
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

}

