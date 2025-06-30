package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "user_info")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    // 사용자 이름 중복 방지를 위한 유니크 제약 조건 설정
    @Column(name = "user_name")
	private String username;
	private String userLoginId;
	private String userPassWord;
	@Column(unique = true)
	private String userEmail;
	private String userAddress;
	private String userPhone;
	private String userBirth;
	private String userGender;
	private String userNickName;
	private String userCivilSerial;

	@CreationTimestamp
    private Timestamp userCreatedAt;
	private int accessLevel;
	private boolean userLockYn;
	private int loginAttemptCont;

    @Builder
    public User(int id, String username, String password, String email, Timestamp createdAt) {
        this.user_id = id;
        this.username = username;
        this.userPassWord = password;
        this.userEmail = email;
        this.userCreatedAt = createdAt;
    }
}
