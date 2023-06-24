package muha.shop.controller;

import muha.shop.entity.OrderStatus;
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

    //сделайте заказ
    @PostMapping("/make_order_post")
    public String makeOrder(@RequestParam String address) {
        orderService.makeOrder(address);
        orderService.deleteCartItemsAfterOrder();
        return "redirect:/order";
    }

    //поиск заказа по пользователю
    @GetMapping("/order")
    public String findOrderByUser(Model model) {
        model.addAttribute("ordersByUser", orderService.findOrderByUser());
        model.addAttribute("statuses", orderService.getAllOrderStatuses());
        return "order";
    }

    //Найти все заказы
    @GetMapping("/show_admin_orders")
    public String findAllOrders(Model model) {
        model.addAttribute("all_orders", orderService.findAllOrders());
        model.addAttribute("statuses", orderService.getAllOrderStatuses());
        return "admin_orders";
    }

    // изменить статус заказа
    @GetMapping("/change_status")
    public String changeOrderStatus(@RequestParam Long orderId, @RequestParam String orderStatus) {
        OrderStatus status = OrderStatus.valueOf(orderStatus);
        orderService.changeStatus(orderId, status);
        return "redirect:/show_admin_orders";
    }
}
