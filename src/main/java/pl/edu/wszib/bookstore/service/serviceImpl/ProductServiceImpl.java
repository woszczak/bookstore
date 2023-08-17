package pl.edu.wszib.bookstore.service.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.dto.ProductDTO;
import pl.edu.wszib.bookstore.mapper.ProductMapper;
import pl.edu.wszib.bookstore.model.Category;
import pl.edu.wszib.bookstore.model.Product;
import pl.edu.wszib.bookstore.repository.CategoryRepository;
import pl.edu.wszib.bookstore.repository.ProductRepository;
import pl.edu.wszib.bookstore.service.ProductService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return productMapper.toDTO(product);
        } else {
            return null;
        }
    }


    @Override
    public List<ProductDTO> getBestsellers() {
        List<Product> bestsellers = productRepository.findByBestseller(true);
        return productMapper.toDTOList(bestsellers);
    }


    @Override
    public ProductDTO save(ProductDTO productDTO)  throws IOException {
        MultipartFile image = productDTO.getImage();
        String imagePath = saveImage(image);

        Product newProduct = new Product();
        newProduct.setName(productDTO.getName());
        newProduct.setPrice(productDTO.getPrice());
        newProduct.setQuantity(productDTO.getQuantity());
        newProduct.setImagePath(imagePath);

        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toDTO(savedProduct);
    }

    private String saveImage(MultipartFile image) throws IOException {

        if ((image == null) || image.isEmpty()){
            return null;
        }
        String uploadDirectory = System.getProperty("user.dir") + "/uploads";
        String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String filePath = Paths.get(uploadDirectory, fileName).toString();
        Files.write(Paths.get(filePath), image.getBytes());
        return filePath;
    }


    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        if (category == null){
            return new ArrayList<>();
        }
        List<Product> products = productRepository.findByCategory(category);
        return productMapper.toDTOList(products);
    }




    @Override
    public ProductDTO edit(Long id, String name, BigDecimal price, Category category, Boolean bestseller, Integer quantity, MultipartFile image) throws IOException {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }
        product.setName(name);
        product.setQuantity(quantity);
        product.setBestseller(bestseller);
        product.setCategory(category);
        product.setPrice(price);


        if (image != null && !image.isEmpty()){
            String imagePath = saveImage(image);
            product.setImagePath(imagePath);
        }
        Product updatedProduct = productRepository.save(product);

        return productMapper.toDTO(updatedProduct);

    }
}
