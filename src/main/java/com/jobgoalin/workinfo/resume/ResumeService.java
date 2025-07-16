package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo._core.errors.exception.Exception404;
import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
import jakarta.persistence.PersistenceUnit;
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

    /**
     * 이력서 등록
     */
    @Transactional
    public void registerResume(ResumeRequest.ResumeRegisterDTO request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

        Resume resume = resumeRepository.save(request.toEntity(user));
        log.info("request 확인 : {}", request);

        SkillList positionSkill = skillListJpaRepository.findBySkillId(request.getPositionId());
        log.info("positionSetting 확인 : {}", positionSkill.getSkillListNo());

        SkillList SkillStack = skillListJpaRepository.findBySkillId(request.getSkillStackId());
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

    /**
     * 이력서 조회
     */
    public Resume findById(Long id) {
        return resumeRepository.findById(id).orElseThrow(() -> {
            return new Exception404("이력서를 찾을 수 없습니다.");
        });
    }

    /**
     * 회원 아이디로 작성한 이력서 목록 조회
     */
    public List<Resume> findResumesByUserId(Long userId) {
        return resumeRepository.findByUserUserId(userId);
    }

    /**
     * 이력서 ID로 조회
     */
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다. ID: " +id));
    }





}



