package pl.edu.wszib.bookstore.model;


import java.math.BigDecimal;
import java.util.List;

public class CartModel {

    private Long id;
    private CartStatusModel cartStatusModel;
    private List<CartItemModel> items ;
    private BigDecimal totalPrice;


    public CartModel(Long id, CartStatusModel cartStatusModel, List<CartItemModel> items, BigDecimal totalPrice) {
        this.id = id;
        this.cartStatusModel = cartStatusModel;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public CartModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartStatusModel getCartStatusModel() {
        return cartStatusModel;
    }

    public void setCartStatusModel(CartStatusModel cartStatusModel) {
        this.cartStatusModel = cartStatusModel;
    }

    public List<CartItemModel> getItems() {
        return items;
    }

    public void setItems(List<CartItemModel> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}

