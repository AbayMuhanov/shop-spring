package muha.shop.controller;

import muha.shop.entity.CartItem;
import muha.shop.entity.Order;
import muha.shop.entity.User;
import muha.shop.repository.CartItemRepository;
import muha.shop.service.CartItemService;
import muha.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class CartItemController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private CartItemService cartItemService;


    // (Уведомление) Товар добавлен в Корзину
    @GetMapping("/add_to_cart")
    public String AddToCartPost(@RequestParam long productId) {
        cartItemService.addItemToCart(productId);
        return "order_processing";
    }

    //    Корзина заказов
    @GetMapping("/cart_of_orders")
    public String addToCart(Model model, @RequestParam(required = false) Long quantityId) {
        User user = userService.getCurrentUser();
        Sort sort = Sort.by(Sort.Order.desc("quantity"));
        List<CartItem> cartItems = cartItemRepository.findAllByUserId(user.getId(), sort);

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", cartItemService.getTotalPriceOfProducts(cartItems));
        model.addAttribute("totalAmount", cartItemService.getTotalAmount(cartItems));
        return "cartItems";
    }

    //    Добавление товара в Корзину
    @PostMapping(path = "/add_to_cart_post")
    public String addItemToCart(@RequestParam Long productId) {
        cartItemService.addItemToCart(productId);
        return "redirect:/product/list";
    }

    @GetMapping("/removeItem")
    public String removeItemFromCart(@RequestParam Long cartItemId) {
        cartItemService.deleteItemFromCart(cartItemId);
        return "redirect:/cartItems";
    }

    @PostMapping("/removeItem_post")
    public String deletePost(@RequestParam Long productId) {
        cartItemService.deleteItemFromCart(productId);
        return "redirect:/cartItems";

    }


    @GetMapping("/removeAllItems")
    public String removeAllItemsFromCart() {
        cartItemService.removeAllItemsFromCart(userService.getCurrentUser().getId());
        return "redirect:/cartItems";
    }

    // Уведомление (Заказ Оформлен)
    @GetMapping(path = "/order_placed")
    public String placeAnOrder() {
        return "product_is_purchased";
    }

    // Оформить заказ В БД
    @PostMapping(path = "/place_an_order_post")
    public String placeAnOrderPost(@RequestParam String deliveryAddress) {
        User user = userService.getCurrentUser();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        Order order = new Order();
        for (CartItem cartItem : cartItems) {
            order.setDeliveryAddress(deliveryAddress);
            cartItemRepository.save(cartItem);
        }
        return "redirect:/product/list";
    }
}
