package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
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
    private final SkillListJpaRepository skillListJpaRepository;
    private final UserSkillListJpaRepository userSkillListJpaRepository;
    private static final Logger log = LoggerFactory.getLogger(ResumeService.class);

    public void registerResume(ResumeRequest.ResumeRegisterDTO request) {
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

        resumeRepository.save(resume);

        SkillList positionSkill = skillListJpaRepository.findBySkillId(request.getPositionId());
        SkillList SkillStack = skillListJpaRepository.findBySkillId(request.getSkillStackId());

        UserSkillList userPositionSetting = UserSkillList.builder()
                .user(user)
                .skillList(positionSkill)
                .instId(request.getUserNickname())
                .build();
        UserSkillList userSkillSetting = UserSkillList.builder()
                .user(user)
                .skillList(SkillStack)
                .instId(request.getUserNickname())
                .build();

        userSkillListJpaRepository.save(userPositionSetting);
        userSkillListJpaRepository.save(userSkillSetting);

    }

    public Resume findById(Long id) {
        return resumeRepository.findById(id).orElseThrow(() -> {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "이력서를 찾을 수 없습니다.");
        });
    }
}
