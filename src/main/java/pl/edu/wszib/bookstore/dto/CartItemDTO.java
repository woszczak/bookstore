package pl.edu.wszib.bookstore.dto;

import pl.edu.wszib.bookstore.model.Cart;
import pl.edu.wszib.bookstore.model.Product;

import java.util.Date;

public class CartItemDTO {

    private Long id;
    private ProductDTO productDTO;
    private Integer quantity;
    private Cart cart;
    private Date createDate;
    private Date updateDate;

    public CartItemDTO(Long id, ProductDTO productDTO, Integer quantity, Cart cart, Date createDate, Date updateDate) {
        this.id = id;
        this.productDTO = productDTO;
        this.quantity = quantity;
        this.cart = cart;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
