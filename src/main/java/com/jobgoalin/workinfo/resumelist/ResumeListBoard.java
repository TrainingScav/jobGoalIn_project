package com.jobgoalin.workinfo.resumelist;

import com.jobgoalin.workinfo.utils.MyDateUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "resume_list")
public class ResumeListBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int resumeNo;

    private int userId;
    private String title;
    private String content;
    private String isExperienced;
    private String isShow;
    private String instId;

    @CreationTimestamp
    private Timestamp instDate;

    // @Builder 제거 (처음엔 단순하게 시작)
    public ResumeListBoard(int resumeNo, int userId, String title, String content, String isExperienced,
                           String isShow, String instId, Timestamp instDate) {
        this.resumeNo = resumeNo;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.isExperienced = isExperienced;
        this.isShow = isShow;
        this.instId = instId;
        this.instDate = instDate;
    }

    public String getFormattedDate() {
        return MyDateUtil.timestampFormat(instDate);
    }

    public String getShowStatus() {
        return "Y".equals(this.isShow) ? "공개" : "비공개";
    }

    public String getExperiencedStatus() {
        return "Y".equalsIgnoreCase(this.isExperienced) ? "O" : "X";
    }

}
