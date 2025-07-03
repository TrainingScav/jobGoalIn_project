package com.jobgoalin.workinfo.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
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
                .userNickName(dto.getUserNickname())
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


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("사용자 조회 실패 - ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다.");
        });
    }


    /**
     *  회원정보 수정 처리 (더티 체킹)
     */

    @Transactional
    public User updateById(Long userId,UserRequest.UpdateDTO updateDTO) {

        User user = findById(userId);

        // 더티 체킹
        user.setUserNickName(updateDTO.getUserNickname());
        user.setUserPhone(updateDTO.getUserPhoneNumber());
        user.setUserPassWord(updateDTO.getUserPassword());
        user.setUserAddress(updateDTO.getUserAddress());
        return user;
    }

}
