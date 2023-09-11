package pl.edu.wszib.bookstore.model;

import pl.edu.wszib.bookstore.entity.Category;

import java.math.BigDecimal;

public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Category category;
    private boolean bestseller;
    private Integer quantity;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, String description, BigDecimal price, Category category, boolean bestseller, Integer quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.bestseller = bestseller;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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