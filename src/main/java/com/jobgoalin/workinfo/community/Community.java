package com.jobgoalin.workinfo.community;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
@Data
@Entity
@Table(name="community_post_info")
@NoArgsConstructor
public class Community {
    private String postId;//pk

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String instId;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp instDate;// 등록일시

    @Builder
    public Community(String postId, String title, String content, String instId, Timestamp instDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.instId = instId;
        this.instDate = instDate;
    }
}

