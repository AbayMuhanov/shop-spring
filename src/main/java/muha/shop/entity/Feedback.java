package muha.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "is_published")
    private boolean isPublished;

    @Column(name = "score_feedback")
    private int scoreFeedback;

    @Column(name = "text_feedback")
    private String textFeedback;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

}
