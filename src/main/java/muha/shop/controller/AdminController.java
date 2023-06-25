package muha.shop.controller;

import muha.shop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public String checkUserFeedbacks(Model model) {
        model.addAttribute("feedbacks", feedbackService.findAllIsPublishedFalse());
        return "admin_feedback";

    }

    @GetMapping("/feedback_post")
    public String postFeedback(@RequestParam Long feedbackId) {
        feedbackService.postFeedback(feedbackId);
//        return "redirect:/admin/feedbacks";
        return "feedback success";

    }
}
