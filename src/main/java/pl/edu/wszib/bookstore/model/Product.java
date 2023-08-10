package pl.edu.wszib.bookstore.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Product name is required.")
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;


    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    @Column(name = "bestseller")
    private Boolean bestseller = false;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "picture")
    @Lob
    private byte[] picture;

    public Boolean isBestseller() {
        return bestseller;
    }

    public void setBestseller(Boolean bestseller) {
        this.bestseller = bestseller;
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

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getBestseller() {
        return bestseller;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
