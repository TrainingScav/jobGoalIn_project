package com.jobgoalin.workinfo.resume;


import com.jobgoalin.workinfo.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Data
@Table(name = "resume_info")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(columnDefinition = "CHAR(1)")
    private char isExperienced;
    @Column(columnDefinition = "CHAR(1)")
    private char isShow;
    private String instId;
    private Timestamp instDate;

    @Builder
    public Resume(Long resumeNo, User user, String title, String content, char isExperienced, char isShow, String instId, Timestamp instDate) {
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
