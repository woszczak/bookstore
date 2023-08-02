package pl.edu.wszib.bookstore.dto;


public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private byte[] picture;

    public ProductDTO(Long id, String name, Double price, byte[] picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.picture = picture;
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
