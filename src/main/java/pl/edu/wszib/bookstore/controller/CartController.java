//package pl.edu.wszib.bookstore.controller;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import pl.edu.wszib.bookstore.model.CartModel;
//import pl.edu.wszib.bookstore.model.CartStatusModel;
//import pl.edu.wszib.bookstore.service.CartService;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/cart")
//public class CartController {
//
//    private CartService cartService;
//
//
//    public CartController(CartService cartService) {
//        this.cartService = cartService;
//    }
//
//
//
////    @PutMapping("/add/{productId}")
////    public CartModel add(@PathVariable Long productId, @RequestParam(required = false, defaultValue = "1") Integer quantity) {
////        return cartService.add(productId, quantity);
////    }
////
////
////    @GetMapping
////    public CartModel get() {
////        return cartService.get();
////    }
////
////    @GetMapping("/{id}")
////    private CartModel get(@PathVariable Long id) {
////        return cartService.get(id);
////    }
////
////
////    @PutMapping("remove/{productId}")
////    private CartModel remove(@PathVariable Long productId,
////                             @RequestParam(required = false, defaultValue = "1") Integer quantity) {
////        return cartService.remove(productId, quantity);
////    }
////
////    @PostMapping("/clear")
////    public CartModel clear() {
////        return cartService.clear();
////    }
////
////
////    @GetMapping("/submitted")
////    public List<CartModel> getSubmittedCarts() {
////        return cartService.getByStatus(CartStatusModel.SUBMITTED);
////    }
////
////
////    @PostMapping("/{cartId}/submit")
////    public CartModel submitCart(@PathVariable Long cartId) {
////        return cartService.submitCart(cartId);
////    }
////
//}
//
//
//
package pl.edu.wszib.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.bookstore.entity.Cart;
import pl.edu.wszib.bookstore.entity.CartItem;
import pl.edu.wszib.bookstore.model.*;
import pl.edu.wszib.bookstore.service.CartItemsService;
import pl.edu.wszib.bookstore.service.CartService;
import pl.edu.wszib.bookstore.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private ProductService productService;
    private CartItemsService cartItemsService;

    public CartController(CartService cartService, ProductService productService, CartItemsService cartItemsService) {
        this.cartService = cartService;
        this.productService = productService;
        this.cartItemsService = cartItemsService;
    }

    @GetMapping
    public String getCart(Model model) {
        CartModel cart = cartService.get();
        model.addAttribute("cart", cart);
        model.addAttribute("cartId", cart.getId());
        model.addAttribute("cartStatusModel", cart.getCartStatusModel());

        List<CartItemModel> cartItems = cartItemsService.list();
        model.addAttribute("cartItems", cartItems);
        return "cartPage";
    }

    @PostMapping("/addToCart/{cartItemId}")
    public String addToCart(
            @PathVariable Long cartItemId,
            @RequestParam Long productId,
            @RequestParam Integer quantity,
            Model model
    ) {
        ProductModel productModel = productService.getById(productId);

        if (productModel == null || productModel.getQuantity() < quantity) {
            return "redirect:/error";
//            dfghjkjhsdsfghjgfdghjklhgf
        }
        Cart cartModel = cartService.getOrCreate();
        model.addAttribute("cart", cartModel);
        CartItemModel cartItemModel = new CartItemModel();
        cartItemModel.setProductModel(productModel);
        cartItemModel.setQuantity(quantity);
        cartService.add(cartItemModel, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{cartItemId}")
    public String removeCartItem(
            @PathVariable Long cartItemId,
            @RequestParam(required = false, defaultValue = "1") Integer quantity
    ) {
        cartService.remove(cartItemId, quantity);

        return "redirect:/cart";
    }

    @GetMapping("/submit")
    public String orderConfirmationPage() {
        return "orderConfirmationPage";
    }


    @PostMapping("/submit")
    public String submitOrder(){
        CartModel submittedCart = cartService.submit();
        if (submittedCart != null) {
            return "redirect:/cart/submit";
        } else {
            return "redirect:/cart";
        }
    }

}



