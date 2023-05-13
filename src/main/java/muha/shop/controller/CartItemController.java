package muha.shop.controller;

import muha.shop.repository.CartItemRepository;
import muha.shop.service.CartItemService;
import muha.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CartItemController {

    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemService cartService;



}
