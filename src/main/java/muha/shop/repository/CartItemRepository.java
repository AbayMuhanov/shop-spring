package muha.shop.repository;

import jakarta.transaction.Transactional;
import muha.shop.entity.CartItem;
import muha.shop.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.Transient;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByUserIdAndProductId(Long userId, Long productId);
    List<CartItem> findAllByUserId(Long userId, Sort sort);
    @Transactional
    void deleteAllByUserId(Long userId);
    List<CartItem> findByUser(User user);

}

