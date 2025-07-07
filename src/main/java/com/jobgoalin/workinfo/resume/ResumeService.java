package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public Resume registerResume(ResumeRequest.ResumeRegisterDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Resume resume = Resume.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .isExperienced(request.getIsExperienced())
                .isShow(request.getIsShow())
                .instId(String.valueOf(user.getUserId())) // 사용자 ID를 기록 (등록자)
                .instDate(new Timestamp(System.currentTimeMillis())) // 현재 시각
                .build();
        return resumeRepository.save(resume);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("사용자 조회 실패 - ID: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND,"사용자를 찾을 수 없습니다.");
        });
    }

}
