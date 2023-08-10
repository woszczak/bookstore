package pl.edu.wszib.bookstore.dto;

import pl.edu.wszib.bookstore.model.CartItem;
import pl.edu.wszib.bookstore.model.CartStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDTO {

    private Long id;
    private CartStatus cartStatus;
    private List<CartItem> items = new ArrayList<>();
    private Date createDate;
    private Date updateDate;


    public CartDTO(Long id, CartStatus cartStatus, List<CartItem> items, Date createDate, Date updateDate) {
        this.id = id;
        this.cartStatus = cartStatus;
        this.items = items;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
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
