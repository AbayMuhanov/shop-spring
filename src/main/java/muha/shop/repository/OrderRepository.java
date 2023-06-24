package muha.shop.repository;

import muha.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("SELECT o FROM Order o ORDER BY o.orderTime ASC")
    List<Order> sortOrderList();


}
