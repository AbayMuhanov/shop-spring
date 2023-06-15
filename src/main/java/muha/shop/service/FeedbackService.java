package muha.shop.service;

import muha.shop.entity.Feedback;
import muha.shop.entity.Product;
import muha.shop.entity.User;
import muha.shop.repository.FeedbackRepository;
import muha.shop.repository.ProductRepository;
import muha.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductRepository productRepository;

    //оставьте отзыв
    public void leaveFeedback(String textFeedback, Integer score, Long productId) {
        if (feedbackRepository.existsByProductIdAndUserId(userService.getCurrentUser().getId(), productId)) {
            return;
        }
        Feedback feedback = new Feedback();
        feedback.setUser(userService.getCurrentUser());
        feedback.setTextFeedback(textFeedback);
        feedback.setScoreFeedback(score);
        feedback.setProduct(productRepository.findById(productId).orElseThrow());
        feedback.setPublishedDate(LocalDateTime.now());
        feedback.setPublished(false);
        feedbackRepository.save(feedback);
    }

    //удалить отзыв
    public void deleteFeedback(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedbackRepository.delete(feedback);
    }

    //оставить отзыв
    public void postFeedback(long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow();
        feedback.setPublished(true);
        feedbackRepository.save(feedback);
    }

    //найдите обратную связь
    public Feedback findFeedback(Long productId) {
        return feedbackRepository.findByUserIdAndProductId(userService.getCurrentUser().getId(), productId);
    }

    //Найти все опубликованное истинно
    public List<Feedback> findAllIsPublishedTrue(Long productId) {
        return feedbackRepository.findAllByProductIdAndIsPublishedTrue(productId);
    }

    //найти все опубликованные данные ложными
    public List<Feedback> findAllIsPublishedFalse() {
        return feedbackRepository.findAllByIsPublishedFalse();
    }

    //получите средний балл обратной связи
    public int getAverageFeedbackScore(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        List<Feedback> feedbacks = feedbackRepository.findAllByProductIdAndIsPublishedTrue(product.getId());
        int average_score = 0;
        if (!feedbacks.isEmpty()) {
            for (Feedback feedback : feedbacks) {
                average_score = average_score + feedback.getScoreFeedback();
            }
            average_score = average_score / feedbacks.size();
        }
        return average_score;
    }

    //получите обратную связь, созданную вовремя
    public String getFeedbackCreatedTime(LocalDateTime dateTime) {
        return dateTime.getHour() + ":" + String.format("%02d", dateTime.getMinute()) + " " +
                String.format("%02d", dateTime.getDayOfMonth()) + "/" + String.format("%02d", dateTime.getMonthValue()) + "/" +
                dateTime.getYear();
    }

    //существует ли обратная связь
    public boolean isFeedbackExist(Long productId) {
        return feedbackRepository.existsByProductIdAndUserId(productId, userService.getCurrentUser().getId());
    }
}

