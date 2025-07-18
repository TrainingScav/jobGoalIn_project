package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo._core.errors.exception.Exception404;
import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final SkillListJpaRepository skillListJpaRepository;
    private final UserSkillListJpaRepository userSkillListJpaRepository;
    private static final Logger log = LoggerFactory.getLogger(ResumeService.class);

    // 이력서 등록
    @Transactional
    public void save(ResumeRequest.ResumeRegisterDTO resumeRegisterDTO) {
        User user = userRepository.findById(resumeRegisterDTO.getUserId())
                .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));
        Resume resume = resumeRepository.save(resumeRegisterDTO.toEntity(user));
        log.info("request 확인 : {}", resumeRegisterDTO);
        SkillList positionSkill = skillListJpaRepository.findBySkillId(resumeRegisterDTO.getPositionId());
        log.info("positionSetting 확인 : {}", positionSkill.getSkillListNo());
        SkillList SkillStack = skillListJpaRepository.findBySkillId(resumeRegisterDTO.getSkillStackId());
        log.info("skillSettiong 확인 : {}", SkillStack.getSkillListNo());
        UserSkillList userPositionSetting = UserSkillList.builder()
                .user(user)
                .resume(resume)
                .skillList(positionSkill)
                .instId(resumeRegisterDTO.getUserNickname())
                .build();
        UserSkillList userSkillSetting = UserSkillList.builder()
                .user(user)
                .resume(resume)
                .skillList(SkillStack)
                .instId(resumeRegisterDTO.getUserNickname())
                .build();
        userSkillListJpaRepository.save(userPositionSetting);
        userSkillListJpaRepository.save(userSkillSetting);
    }

    // 이력서 조회
    public Resume findResumeById(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() ->
                new Exception404("해당 이력서는 존재하지 않습니다.")
        );
        return resume;
    }

    // 회원 ID로 해당 회원 이력서 목록 조회
    public List<Resume> findResumesByUserId(Long userId) {
        return resumeRepository.findByUserUserId(userId);
    }

    // 이력서 ID로 이력서 조회하기
    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다. ID: " + id));
    }

    // 이력서 수정
    @Transactional
    public Resume update(Long id, ResumeRequest.UpdateDTO updateDTO) {

        Resume updateResume = findResumeById(id);

        updateResume.setTitle(updateDTO.getTitle());
        updateResume.setName(updateDTO.getUserName());
        updateResume.setPhoneNumber(updateResume.getPhoneNumber());
        updateResume.setAddress(updateDTO.getAddress());
        updateResume.setBirthdate(updateDTO.getBirthdate());
        updateResume.setEmail(updateDTO.getEmail());
        updateResume.setGender(updateDTO.getGender());
        updateResume.setContent(updateDTO.getContent());
        updateResume.setIsExperienced(updateDTO.getIsExperienced());
        updateResume.setIsShow(updateDTO.getIsShow());

        return updateResume;
    }


}



