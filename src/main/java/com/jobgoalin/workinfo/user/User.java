package com.jobgoalin.workinfo.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "user_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

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
	@Column(nullable = false, columnDefinition = "CHAR(1)")
	private String userGender;
	@Column(nullable = false)
	private String userNickName;
	@Column(unique = true ,nullable = false)
	private String userCivilSerial;

	@Column(nullable = false)
	private LocalDateTime userCreatedAt;

	@ColumnDefault("1")
	private Long accessLevel;

	@ColumnDefault("'N'")
	@Column(columnDefinition = "CHAR(1)")
	private char userLockYn;

	@ColumnDefault("0")
	private Long loginAttemptCount;

	@Builder
	public User(Long userId, String username, String userLoginId, String userPassWord, String userEmail, String userAddress, String userPhone, String userBirth, String userGender, String userNickName, String userCivilSerial, LocalDateTime userCreatedAt, Long accessLevel, char userLockYn, Long loginAttemptCount) {
		this.userId = userId;
		this.username = username;
		this.userLoginId = userLoginId;
		this.userPassWord = userPassWord;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userBirth = userBirth;
		this.userGender = userGender;
		this.userNickName = userNickName;
		this.userCivilSerial = userCivilSerial;
		this.userCreatedAt = userCreatedAt;
		this.accessLevel = accessLevel;
		this.userLockYn = userLockYn;
		this.loginAttemptCount = loginAttemptCount;
	}


}
