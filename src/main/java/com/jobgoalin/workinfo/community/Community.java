package com.jobgoalin.workinfo.community;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "community_post_info")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(name = "inst_id", length = 50, nullable = false)
    private String instId;

    @CreationTimestamp
    @Column(name = "inst_date", nullable = false, updatable = false)
    private Timestamp instDate;

    @Builder
    public Community(String title, String content, String instId) {
        this.title = title;
        this.content = content;
        this.instId = instId;
    }
}