package pl.edu.wszib.bookstore.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.exceptions.NotFound;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.repository.CategoryRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ProductMapper productMapper;


    public ProductServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {

        return productMapper.toDTOList(productRepository.findAll());
    }

    @Override
    public ProductDTO getProduct(Long id) {

        return productRepository.findById(id).map(productMapper::toDTO).orElseThrow(() -> new NotFound(id, Product.class));
    }


    @Override
    public List<ProductDTO> getBestsellers() {
        List<Product> bestsellers = productRepository.findByBestseller(true);
        return productMapper.toDTOList(bestsellers);
    }


    @Override
    public ProductDTO save(ProductDTO productDTO) throws IOException {
        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setDescription(productDTO.getDescription());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setQuantity(productDTO.getQuantity());
        newProduct.setBestseller(productDTO.isBestseller());
        newProduct.setCategory(productDTO.getCategory());

        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toDTO(savedProduct);
    }


    @Override
    public void delete(Long id) {

    productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            return new ArrayList<>();
        }
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toDTOList(products);
    }


    @Override
    public List<ProductDTO> searchProductByName(String keyword) {
        List<ProductDTO> productDTOS = productRepository.searchProductByName(keyword)
                .stream()
                .map(productMapper::toDTO).collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public ProductDTO edit(Long id, String name, String description, BigDecimal price, String categoryName, Boolean bestseller, Integer quantity) throws IOException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFound(id, Product.class));

        if (product == null) {
            return null;
        }


        Category category = categoryRepository.findByName(categoryName);
        if (category == null) {
            throw new IllegalArgumentException("Invalid categoryId");
        }
        product.setName(name);
        product.getDescription();
        product.setQuantity(quantity);
        product.setBestseller(bestseller);
        product.setCategory(category);
        product.setPrice(price);


        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);

    }

    @Override
    public List<ProductDTO> searchProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        List<Product> products = productRepository.searchProductsByPriceRange(minPrice, maxPrice);
        return productMapper.toDTOList(products);
    }

    @Override
    public List<ProductDTO> getAllProductsSortedByPriceAsc() {
        List<Product> products = productRepository.findAllByOrderByPriceAsc();
        return productMapper.toDTOList(products);
    }

    @Override
    public List<ProductDTO> getAllProductsSortedByPriceDesc() {
        List<Product> products = productRepository.findAllByOrderByPriceDesc();
        return productMapper.toDTOList(products);
    }

}
