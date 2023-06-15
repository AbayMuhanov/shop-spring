package muha.shop.controller;

import muha.shop.entity.Feedback;
import muha.shop.repository.FeedbackRepository;
import muha.shop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    private FeedbackRepository feedbackRepository;

    //    оставьте отзыв
    @PostMapping("/leave_feedback_post")
    public String leaveFeedback(@RequestParam("textFeedback") String textFeedback,
                                @RequestParam("score") Integer score,
                                @RequestParam("productId") Long productId, Model model) {
        Feedback feedback = feedbackRepository.findByScoreFeedback(score);
        model.addAttribute("feedback",feedback);
        feedbackService.leaveFeedback(textFeedback, score, productId);
        feedbackService.getAverageFeedbackScore(productId);
        return "redirect:/product/show?productId=" + productId;
    }


    //    Уведомление Отзыв отправлен успешно
    @GetMapping("/feedback")
    public String FeedBack() {
        return "feedback";
    }

    //удалить отзыв
    @GetMapping("/delete_feedback")
    public String deleteFeedback(@RequestParam(required = false) Long feedbackId,
                                 @RequestParam(required = false) Long productId) {
        feedbackService.deleteFeedback(feedbackId);
        return "redirect:/product/show?productId=" + productId;
    }

}

