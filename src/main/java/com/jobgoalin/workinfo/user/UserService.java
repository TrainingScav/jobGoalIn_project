package com.jobgoalin.workinfo.user;

import com.jobgoalin.workinfo._core.errors.exception.Exception400;
import com.jobgoalin.workinfo._core.errors.exception.Exception404;
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
    private final CompUserRepository compUserRepository;

    // 일반유저 회원가입 - 유니크 컬럼만 검증함
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
                .userPhoneNumber(dto.getUserPhoneNumber())
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

    // 기업유저 회원가입 - 유니크 컬럼만 검증함
    public void compJoin(UserRequest.CompJoinDTO dto) {
        dto.validate();
        if (userRepository.existsByUserLoginId(dto.getCompUserLoginId())) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }
        if (userRepository.existsByUserEmail(dto.getCompUserEmail())) {
            throw new IllegalArgumentException("이메일이 이미 사용 중입니다.");
        }

        CompUser compUser = CompUser.builder()
                .compUserName(dto.getCompUserName())
                .compUserLoginId(dto.getCompUserLoginId())
                .compUserPassword(dto.getCompUserPassword())
                .compUserPhone(dto.getCompUserPhone())
                .compUserEmail(dto.getCompUserEmail())
                .compUserNickname(dto.getCompUserNickname())
                .compRegNumber(dto.getCompRegNumber())
                .compName(dto.getCompName())
                .compCEOName(dto.getCompCEOName())
                .compAddress(dto.getCompAddress())
                .accessLevel(2L)
                .userLockYn('N')
                .loginAttemptCount(0L)
                .build();

        compUserRepository.save(compUser);
    }

    // 일반 회원 로그인
    public User login(UserRequest.LoginDTO dto) {
        // 아이디 조회
        User user = userRepository.findByUserLoginId(dto.getUserLoginId())
                .orElseThrow(() -> new Exception400("아이디가 존재하지 않습니다."));
        // 조회 후 계정잠금 확인
        if (user.getUserLockYn() == 'Y') {
            throw new Exception400("로그인 시도 5회 초과로 계정이 잠금되었습니다.");
        }
        // 입력한 비밀번호가 다르다면
        if (!dto.getUserPassWord().equals(user.getUserPassWord())) {
            user.setLoginAttemptCount(user.getLoginAttemptCount() + 1);
            //로그인 횟수 1 추가
            if (user.getLoginAttemptCount() >= 5) {
                user.setUserLockYn('Y');
            }

            throw new Exception400("비밀번호가 틀렸습니다. 5회 누적시 계정 잠김." + " 현재 시도 횟수 : "+ user.getLoginAttemptCount());
        }

        // 성공 시 초기화
        user.setLoginAttemptCount(0L);
        user.setUserLockYn('N');

        return user;
    }

    // 기업 회원 로그인
    public CompUser compLogin(UserRequest.LoginDTO dto) {

        return compUserRepository
                .findByUserIdAndPassword(dto.getUserLoginId(), dto.getUserPassWord())
                .orElseThrow(() -> new Exception400("사용자명 또는 비밀번호가 틀렸어요"));
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
        user.setUserPhoneNumber(updateDTO.getUserPhoneNumber());
        user.setUserPassWord(updateDTO.getUserPassword());
        user.setUserAddress(updateDTO.getUserAddress());
        return user;
    }

    public CompUser findCompUserById(Long id) {

        return compUserRepository.findById(id).orElseThrow(() -> {
            return new Exception404("해당 유저를 찾을 수 없습니다.");
        });
    }
}
