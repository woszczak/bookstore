package pl.edu.wszib.bookstore.dto;


import jdk.jfr.Category;

import java.util.Calendar;

public class ProductDTO {
    private Long id;

    private String name;

    private Double price;
    private Category category;

    private byte[] picture;

    public ProductDTO(Long id, String name, Double price, Category category, byte[] picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
