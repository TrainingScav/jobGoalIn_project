package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.company.CompanyReview;
import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserSkillList;
import com.jobgoalin.workinfo.utils.MyDateUtil;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String birthdate;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false, length = 1)
    private char gender;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 1)
    private char isExperienced;
    @Column(nullable = false, length = 1)
    private char isShow;
    private String instId;

    @CreationTimestamp
    private Timestamp instDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume", cascade = CascadeType.REMOVE)
    List<UserSkillList> userSkillLists = new ArrayList<>(); // List 선언과 동시에 초기화

    @Transient
    private String position;
    @Transient
    private String skill; // DB에 저장되지 않음

    @Builder
    public Resume(Long resumeNo, User user, String title, String name, String phoneNumber, String address, String birthdate, String email, char gender, String content, char isExperienced, char isShow, String instId, Timestamp instDate) {
        this.resumeNo = resumeNo;
        this.user = user;
        this.title = title;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
        this.content = content;
        this.isExperienced = isExperienced;
        this.isShow = isShow;
        this.instId = instId;
        this.instDate = instDate;
    }

    public String getFormattedDate() {
        return MyDateUtil.timestampFormat(instDate);
    }

    public boolean getShowStatus() {
        return this.isShow == 'Y';
    }

    public boolean getExperiencedStatus() {
        return this.isExperienced == 'Y';
    }

    public boolean getGenderInfo() {
        return this.gender == 'M';
    }
}
