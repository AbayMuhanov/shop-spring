package muha.shop.controller;


import muha.shop.entity.Order;
import muha.shop.entity.OrderStatus;
import muha.shop.repository.OrderRepository;
import muha.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    //    сделать_заказ
    @GetMapping("/make_order")
    public String chooseAddressAndStatus(Model model, String deliveryAddress) {
        model.addAttribute("deliveryAddress", deliveryAddress);
        return "order_product";
    }

    @PostMapping("/make_order_post")
    public String makeOrder(@RequestParam String deliveryAddress) {
        orderService.makeOrder(deliveryAddress);
        return "redirect:/product/order_post";
    }

    // изменить статус
    @GetMapping("/change_status")
    public String chooseOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus orderStatus, Model model) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        model.addAttribute("order", order);
        return "cart";
    }

    @PostMapping("/change_status")
    public String changeStatus(@RequestParam Long orderId) {
        orderService.changeStatus(orderId);
        return "redirect:/product/list";
    }


}
