package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "resume_info")
public class ResumeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeNo;

    //userId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키 컬럼명 명시
    private User user;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 1)
    private char isExperienced;
    @Column(length = 1)
    private char isShow;

    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

    @Builder
    public ResumeInfo(Long resumeNo, User user, String title, String content, char isExperienced, char isShow, String instId, Timestamp instDate) {
        this.resumeNo = resumeNo;
        this.user = user;
        this.title = title;
        this.content = content;
        this.isExperienced = isExperienced;
        this.isShow = isShow;
        this.instId = instId;
        this.instDate = instDate;
    }
}
