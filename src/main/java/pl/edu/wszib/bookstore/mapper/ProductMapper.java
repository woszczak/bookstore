package pl.edu.wszib.bookstore.mapper;

import org.springframework.stereotype.Component;
import pl.edu.wszib.bookstore.entity.Product;
import pl.edu.wszib.bookstore.model.ProductModel;


@Component
public class ProductMapper {

   public static Product toEntity(ProductModel productModel){
       Product product = new Product();
       product.setId(productModel.getId());
       product.setDescription(productModel.getDescription());
       product.setBestseller(productModel.isBestseller());
       product.setCategory(productModel.getCategory());
       product.setName(productModel.getName());
       product.setPrice(productModel.getPrice());
       product.setQuantity(productModel.getQuantity());
       return product;
   }

   public static ProductModel toModel(Product product){

       ProductModel productModel = new ProductModel();
       productModel.setId(product.getId());
       productModel.setDescription(product.getDescription());
       productModel.setBestseller(product.isBestseller());
       productModel.setCategory(product.getCategory());
       productModel.setName(product.getName());
       productModel.setPrice(product.getPrice());
       productModel.setQuantity(product.getQuantity());
       return productModel;
   }

}




