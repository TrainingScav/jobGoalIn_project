package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Table(name = "comp_user_tb")
@Entity

@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int compUserId;

    // 사용자 이름 중복 방지를 위한 유니크 제약 조건 설정
    @Column(unique = true,nullable = false)
    private String compUserLoginId;
    @Column(nullable = false)
    private String compUserPassword;
    @Column(nullable = false)
    private String compUserPhone;
    @Column(nullable = false)
    private String compUserEmail;
    @Column(nullable = false)
    private String compUserNickname;
    @Column(nullable = false)
    private String compTypeCode;
    @Column(nullable = false)
    private int compRegNumber;
    @Column(nullable = false)
    private String compName;
    @Column(nullable = false)
    private String compCEOName;
    @Column(nullable = false)
    private String compAddress;

    @CreationTimestamp
    private Timestamp ComUserCreatedAt;
    @JoinColumn(nullable = false)
    private int accessLevel;
    private boolean userLockYn = false;
    private int loginAttemptCount = 0;

}
