package pl.edu.wszib.bookstore.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Long id;
    private CartStatusDTO cartStatusDTO;
    private List<CartItemDTO> items ;
    private BigDecimal totalPrice;


    public CartDTO(Long id, CartStatusDTO cartStatusDTO, List<CartItemDTO> items, BigDecimal totalPrice) {
        this.id = id;
        this.cartStatusDTO = cartStatusDTO;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartStatusDTO getCartStatusDTO() {
        return cartStatusDTO;
    }

    public void setCartStatusDTO(CartStatusDTO cartStatusDTO) {
        this.cartStatusDTO = cartStatusDTO;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}

