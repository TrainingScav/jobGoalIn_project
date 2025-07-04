package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.utils.MyDateUtil;
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
    private boolean isCurrentEmployee;

    @Column(nullable = false)
    private boolean isRecommended;

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

    /**
     * 데이터 베이스에 생성이 안되는 필드 (즉 변수)
     * why use it? - 뷰에 현재 로그인한 사용자가 여러개의 댓글 중 내가 작성한
     * 댓글은 삭제 기능을 추가하기 위해 편의성 변수를 할당한다.
     */
    @Transient
    private Boolean isMyReview;

    public boolean isOwner(Long sessionId) {
        return this.user.getUserId().equals(sessionId);
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(instDate);
    }
}
