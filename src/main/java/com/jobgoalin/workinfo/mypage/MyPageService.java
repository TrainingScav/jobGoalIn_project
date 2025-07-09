package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.resume.UserSkillListJpaRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserSkillListJpaRepository userSkillListJpaRepository;

    @Transactional
    public List<UserSkillList> getUserSkillsByUserId(Long userId) {
        return userSkillListJpaRepository.findByUser_UserId(userId);
    }
}
