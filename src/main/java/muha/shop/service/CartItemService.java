package muha.shop.service;

import muha.shop.entity.CartItem;
import muha.shop.entity.Product;
import muha.shop.entity.User;
import muha.shop.repository.CartItemRepository;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    public void addItemToCart(Long productId) {
        User user = userService.getCurrentUser();
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), productId);

        if (cartItem != null) {
            cartItem.setQuantity(+1);
            cartItemRepository.save(cartItem);
        } else {
            CartItem newCartItem = new CartItem();
            newCartItem.setUser(user);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(1);
            cartItemRepository.save(newCartItem);
        }
    }

//    public void increaseByOne(Long cartItemId) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
//        cartItem.setQuantity(cartItem.getQuantity() + 1);
//        cartItemRepository.save(cartItem);
//    }
//
//    public void decreaseByOne(Long cartItemId) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
//
//        cartItem.setQuantity(cartItem.getQuantity() - 1);
//        if (cartItem.getQuantity() <= 0) {
//            cartItem.setQuantity(+1);
//        }
//        cartItemRepository.save(cartItem);
//    }

    public void deleteItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void removeAllItemsFromCart(Long cartItemsId) {
        cartItemRepository.deleteAllByUserId(cartItemsId);
    }

    public int getTotalPriceOfProducts(List<CartItem> cartItems) {
        int totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            totalPrice = totalPrice + getPrice(cartItem);
        }
        return totalPrice;
    }

//    public String getFormattedTotalPrice(int totalPrice) {
//        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
//        symbols.setGroupingSeparator(' ');
//        DecimalFormat decimalFormat = new DecimalFormat("#,##0", symbols);
//        return decimalFormat.format(totalPrice);
//    }


    public int getPrice(CartItem cartItem) {
        return cartItem.getProduct().getPrice() * cartItem.getQuantity();
    }
    public int getTotalAmount(List <CartItem> cartItems) {
        int totalAmount = 0;
        for (CartItem cartItem : cartItems) {
            totalAmount = totalAmount + cartItem.getQuantity();
        }
        return totalAmount;
    }

}



