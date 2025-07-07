package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comp_user_info")
public class CompUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long compUserId;

    // 사용자 이름 중복 방지를 위한 유니크 제약 조건 설정
    @Column(nullable = false)
    private String compUserName;
    @Column(unique = true,nullable = false)
    private String compUserLoginId;
    @Column(nullable = false)
    private String compUserPassword;
    @Column(nullable = false)
    private String compUserPhone;
    @Column(unique = true, nullable = false)
    private String compUserEmail;
    @Column(nullable = false)
    private String compUserNickname;
    /*
    @Column(nullable = false)
    private String compTypeCode;
    */
    @Column(nullable = false)
    private Long compRegNumber;
    @Column(nullable = false)
    private String compName;
    @Column(nullable = false)
    private String compCEOName;
    @Column(nullable = false)
    private String compAddress;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp ComUserCreatedAt;

    @ColumnDefault("2")
    private Long accessLevel;

    @ColumnDefault("'N'")
    @Column(columnDefinition = "CHAR(1)")
    private char userLockYn;

    @ColumnDefault("0")
    private Long loginAttemptCount;

    @Builder

    public CompUser(String compUserName, String compUserLoginId, String compUserPassword, String compUserPhone, String compUserEmail, String compUserNickname, Long compRegNumber, String compName, String compCEOName, String compAddress) {
        this.compUserName = compUserName;
        this.compUserLoginId = compUserLoginId;
        this.compUserPassword = compUserPassword;
        this.compUserPhone = compUserPhone;
        this.compUserEmail = compUserEmail;
        this.compUserNickname = compUserNickname;
        this.compRegNumber = compRegNumber;
        this.compName = compName;
        this.compCEOName = compCEOName;
        this.compAddress = compAddress;
    }
}
