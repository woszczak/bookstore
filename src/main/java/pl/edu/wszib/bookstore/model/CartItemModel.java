package pl.edu.wszib.bookstore.model;

import pl.edu.wszib.bookstore.entity.Cart;

import java.util.Date;

public class CartItemModel {

    private Long id;
    private ProductModel productModel;
    private Integer quantity;
    private Cart cart;
    private Date createDate;
    private Date updateDate;

    public CartItemModel(Long id, ProductModel productModel, Integer quantity, Cart cart, Date createDate, Date updateDate) {
        this.id = id;
        this.productModel = productModel;
        this.quantity = quantity;
        this.cart = cart;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public CartItemModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
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
