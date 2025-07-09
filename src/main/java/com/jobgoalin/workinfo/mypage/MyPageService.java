package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.resume.SkillListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final SkillListJpaRepository skillListJpaRepository;

    public List<SkillList> getUserKills(Long userId) {
        return skillListJpaRepository.findByUser_UserId(userId);
    }
}
