package muha.shop.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus order_status;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;

}
