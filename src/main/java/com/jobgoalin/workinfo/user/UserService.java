package com.jobgoalin.workinfo.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입 - 유니크 컬럼만 검증함
    public void join(UserRequest.JoinDTO dto) {
        if (userRepository.existsByUserLoginId(dto.getUserLoginId())) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }
        if (userRepository.existsByUserEmail(dto.getUserEmail())) {
            throw new IllegalArgumentException("이메일이 이미 사용 중입니다.");
        }
        if (userRepository.existsByUserCivilSerial(dto.getUserCivilSerial())) {
            throw new IllegalArgumentException("주민번호가 이미 등록되어 있습니다.");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .userLoginId(dto.getUserLoginId())
                .userPassWord(dto.getUserPassWord())
                .userEmail(dto.getUserEmail())
                .userAddress(dto.getUserAddress())
                .userPhone(dto.getUserPhone())
                .userBirth(dto.getUserBirth())
                .userGender(dto.getUserGender())
                .userNickName(dto.getUserNickName())
                .userCivilSerial(dto.getUserCivilSerial())
                .userCreatedAt(LocalDateTime.now())
                .accessLevel(1L)
                .userLockYn('N')
                .loginAttemptCount(0L)
                .build();

        userRepository.save(user);
    }

    // 로그인
    public User login(UserRequest.LoginDTO dto) {
        Optional<User> optionalUser = userRepository.findByUserLoginId(dto.getUserLoginId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserPassWord().equals(dto.getUserPassWord())) {
                return user;
            }
        }

        return null; // 아이디나 비밀번호 없으면 null 반환
    }

}
