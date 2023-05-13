package muha.shop.service;

import muha.shop.entity.CartItem;
import muha.shop.entity.Product;
import muha.shop.entity.User;
import muha.shop.repository.CartItemRepository;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

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

    public void increaseByOne(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItemRepository.save(cartItem);
    }

    public void decreaseByOne(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow();

        cartItem.setQuantity(cartItem.getQuantity() - 1);
        if (cartItem.getQuantity() <= 0) {
            cartItem.setQuantity(+1);
        }
        cartItemRepository.save(cartItem);
    }


    public void deleteItemFromCart(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }
}



