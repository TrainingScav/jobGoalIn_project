package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final SkillListJpaRepository skillListJpaRepository;
    private final UserSkillListJpaRepository userSkillListJpaRepository;
    private static final Logger log = LoggerFactory.getLogger(ResumeService.class);

    @Transactional
    public void registerResume(ResumeRequest.ResumeRegisterDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Resume resume = resumeRepository.save(request.toEntity(user));

        log.info("request 확인 : {}", request);

        SkillList positionSkill = skillListJpaRepository.findBySkillId(request.getPositionId());
        SkillList SkillStack = skillListJpaRepository.findBySkillId(request.getSkillStackId());

        log.info("positionSetting 확인 : {}", positionSkill.getSkillListNo());
        log.info("skillSettiong 확인 : {}", SkillStack.getSkillListNo());

        UserSkillList userPositionSetting = UserSkillList.builder()
                .user(user)
                .resume(resume)
                .skillList(positionSkill)
                .instId(request.getUserNickname())
                .build();
        UserSkillList userSkillSetting = UserSkillList.builder()
                .user(user)
                .resume(resume)
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

    public List<Resume> findResumesByUserId(Long userId) {
        return resumeRepository.findByUserUserId(userId);
    }
}
