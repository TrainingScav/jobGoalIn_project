package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "user_tb")
@Entity
public class CompUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 이름 중복 방지를 위한 유니크 제약 조건 설정
    @Column(unique = true)
    private String comUserLoginId ;

    private String comUserPassword;
    private String compUserPhone;
    private String compUserEmail;
    private String compUserNickname;
    private String compTypeCode;
    private int compRegNumber;
    private String compName;
    private String compCEOName;
    private String compAddress;

    @CreationTimestamp
    private Timestamp compUserCreatedAt;
    private int accessLevel;
    private
    // 객체 생성 시 가독성과 안정성 향상
    @Builder
    public CompUser(Long id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
}
