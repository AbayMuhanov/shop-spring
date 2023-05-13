package muha.shop.repository;

import muha.shop.entity.CartItem;
import muha.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByUserIdAndProductId(Long userId, Long productId);

    List<CartItem> findByUser(User user);

    CartItem findByProductId(Long productId);
}
