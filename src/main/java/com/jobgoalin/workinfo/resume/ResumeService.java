package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(ResumeService.class);

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

    public Resume findById(Long id) {
        return resumeRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "이력서를 찾을 수 없습니다.");
        });
    }
}
