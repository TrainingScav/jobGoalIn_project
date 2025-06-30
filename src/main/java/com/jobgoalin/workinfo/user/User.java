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
    private int userId;

	@Column(nullable = false)
	private String username;
	@Column(unique = true ,nullable = false)
	private String userLoginId;
	@Column(nullable = false)
	private String userPassWord;
	@Column(unique = true,nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String userAddress;
	@Column(nullable = false)
	private String userPhone;
	@Column(nullable = false)
	private String userBirth;
	@Column(nullable = false)
	private String userGender;
	@Column(nullable = false)
	private String userNickName;
	@Column(unique = true ,nullable = false)
	private String userCivilSerial;

	@CreationTimestamp
	@Column(nullable = false)
    private Timestamp userCreatedAt;
	@JoinColumn(nullable = false)
	private int accessLevel;
	private boolean userLockYn = false;
	private int loginAttemptCount = 0;

//    @Builder
//    public User(int id, String username, String password, String email, Timestamp createdAt) {
//        this.userId = id;
//        this.username = username;
//        this.userPassWord = password;
//        this.userEmail = email;
//        this.userCreatedAt = createdAt;
//    }
}
