package pl.edu.wszib.bookstore.dto;


import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.bookstore.model.Category;

import java.math.BigDecimal;

public class ProductDTO {
    private Long id;

    private String name;

    private BigDecimal price;
    private Category category;

    private MultipartFile image;


    private boolean bestseller;
    private Integer quantity;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, BigDecimal price, Category category, MultipartFile image, boolean bestseller, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.bestseller = bestseller;
        this.quantity = quantity;
    }


    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public boolean isBestseller() {
        return bestseller;
    }

    public void setBestseller(boolean bestseller) {
        this.bestseller = bestseller;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}