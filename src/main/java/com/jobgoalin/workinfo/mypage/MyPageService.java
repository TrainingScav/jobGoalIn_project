package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.resume.UserSkillListJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserSkillListJpaRepository userSkillListJpaRepository;

    }

