package pl.edu.wszib.bookstore.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.repository.ProductRepository;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        return productMapper.toDTOList(productRepository.findAll());
    }

    @Override
    public ProductDTO getProduct(Long id) {

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return productMapper.toDTO(product);
        } else {
           return null;
        }
    }
    @Override
    public List<ProductDTO> getProductsByCategory(Category category) {
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toDTOList(products);
    }


    @Override
    public List<ProductDTO> getBestsellers() {
        List<Product> bestsellers = productRepository.findByBestseller(true);
        return productMapper.toDTOList(bestsellers);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
       Product product = productMapper.toDB(productDTO);
        if (product.getQuantity() < 0) {

            product.setQuantity(1);
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO edit(Long id, String name, BigDecimal price, Category category, Boolean bestseller, Integer quantity, MultipartFile picture) throws IOException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null){
            return null;
        }
        product.setName(name);
        product.setQuantity(quantity);
        product.setBestseller(bestseller);
        product.setCategory(category);
        product.setPrice(price);

        if (picture != null && !picture.isEmpty()){
            product.setPicture(picture.getBytes());
        }

        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);

    }


}
