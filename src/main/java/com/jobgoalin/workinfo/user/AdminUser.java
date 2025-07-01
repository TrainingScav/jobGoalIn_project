package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "admin_user_info")
@Entity
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    // 사용자 이름 중복 방지를 위한 유니크 제약 조건 설정
    @Column(unique = true,nullable = false)
    private String adminLoginId;
    @Column(nullable = false)
    private String adminPassword;
    @Column(nullable = false)
    private String adminEmail;

    // 엔티티가 영속화 될 때 자동으로 pc 현재 시간을 설정
    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdAt;

    @ColumnDefault("3")
    private Long accessLevel;

    @ColumnDefault("'N'")
    @Column(columnDefinition = "CHAR(1)")
    private char userLockYn;

    @ColumnDefault("0")
    private Long loginAttemptCount;

//    // 객체 생성 시 가독성과 안정성 향상
//    @Builder
//    public User(Long id, String username, String password, String email, Timestamp createdAt) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.createdAt = createdAt;
//    }
}
