

package pl.edu.wszib.bookstore.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.CartStatus;
import pl.edu.wszib.bookstore.model.CartStatusModel;


@Component
public class CartStatusMapper {

    public static CartStatusModel toModel(CartStatus entity) {
        if (entity == null) {
            return null;
        }
        switch (entity) {
            case NEW:
                return CartStatusModel.NEW;
            case SUBMITTED:
                return CartStatusModel.SUBMITTED;
            default:
                throw new IllegalArgumentException("Nieobsługiwany enum: " + entity);
        }
    }

    public static CartStatus toEntity(CartStatusModel model) {
        if (model == null) {
            return null;
        }
        switch (model) {
            case NEW:
                return CartStatus.NEW;
            case SUBMITTED:
                return CartStatus.SUBMITTED;
            default:
                throw new IllegalArgumentException("Nieobsługiwany enum: " + model);
        }
    }
}
