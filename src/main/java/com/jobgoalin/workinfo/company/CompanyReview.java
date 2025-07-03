package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "company_review")
public class CompanyReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyInfo companyInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 400)
    private String content;

    @Column(nullable = false)
    private Boolean isCurrentEmployee;

    @Column(nullable = false)
    private Boolean isRecommended;

    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

    @Builder
    public CompanyReview(Long reviewId, CompanyInfo companyInfo, User user, String content, Boolean isCurrentEmployee, Boolean isRecommended, String instId, Timestamp instDate) {
        this.reviewId = reviewId;
        this.companyInfo = companyInfo;
        this.user = user;
        this.content = content;
        this.isCurrentEmployee = isCurrentEmployee;
        this.isRecommended = isRecommended;
        this.instId = instId;
        this.instDate = instDate;
    }
}
