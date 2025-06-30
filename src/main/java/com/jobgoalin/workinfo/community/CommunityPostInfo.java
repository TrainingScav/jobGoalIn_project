package com.jobgoalin.workinfo.community;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="community_post_info")
@NoArgsConstructor
public class CommunityPostInfo {
    private String postId;//pk
    private String title;
    private String content;
    private String instId;

    @CreationTimestamp
    private Timestamp instDate;// 등록일시

    @Builder
    public CommunityPostInfo(String postId, String title, String content, String instId, Timestamp instDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.instId = instId;
        this.instDate = instDate;
    }
}
