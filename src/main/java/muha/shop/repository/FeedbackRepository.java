package muha.shop.repository;

import muha.shop.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByProductIdAndIsPublishedTrue(Long productId);

    List<Feedback> findAllByIsPublishedFalse();

    Feedback findByUserIdAndProductId(Long userId, Long productId);

    boolean existsByProductIdAndUserId(Long productId, Long userId);

//    Feedback findByScoreFeedback(Integer score);


}

