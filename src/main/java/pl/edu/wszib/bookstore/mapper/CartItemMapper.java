
package pl.edu.wszib.bookstore.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.CartItem;
import pl.edu.wszib.bookstore.model.CartItemModel;


@Component
public class CartItemMapper {

    public static CartItem toEntity(CartItemModel cartItemModel) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemModel.getId());
        cartItem.setProduct(ProductMapper.toEntity(cartItemModel.getProductModel()));
        cartItem.setQuantity(cartItemModel.getQuantity());
        cartItem.setCart(cartItemModel.getCart());
        cartItem.setCreateDate(cartItemModel.getCreateDate());
        cartItem.setUpdateDate(cartItemModel.getUpdateDate());
        return cartItem;
    }

    public static CartItemModel toModel(CartItem cartItem) {
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setId(cartItem.getId());
        cartItemModel.setProductModel(ProductMapper.toModel(cartItem.getProduct()));
        cartItemModel.setQuantity(cartItem.getQuantity());
        cartItemModel.setCart(cartItem.getCart());
        cartItemModel.setCreateDate(cartItem.getCreateDate());
        cartItemModel.setUpdateDate(cartItem.getUpdateDate());
        return cartItemModel;
    }
}
