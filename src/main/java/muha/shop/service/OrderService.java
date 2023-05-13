package muha.shop.service;

import muha.shop.entity.CartItem;
import muha.shop.entity.Order;
import muha.shop.entity.OrderProduct;
import muha.shop.entity.OrderStatus;
import muha.shop.repository.CartItemRepository;
import muha.shop.repository.OrderProductRepository;
import muha.shop.repository.OrderRepository;
import muha.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

//    сделайте заказ
    public void makeOrder(String deliveryAddress) {
        List<CartItem> cartItems = cartItemRepository.findByUser(userService.getCurrentUser());

        Order order = new Order();
        order.setUser(userService.getCurrentUser());
        order.setOrder_status(OrderStatus.SEND);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderTime(LocalDateTime.now());
        orderRepository.save(order);

        for (int i = 0; i < cartItems.size(); i++) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(order);
            orderProduct.setProduct(cartItems.get(i).getProduct());
            orderProduct.setQuantity(cartItems.get(i).getQuantity());
            orderProductRepository.save(orderProduct);
        }
    }

//    изменить статус
    public void changeStatus(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrder_status(order.getOrder_status());
        orderRepository.save(order);
    }
}

