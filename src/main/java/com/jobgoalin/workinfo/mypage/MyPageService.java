package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.resume.Resume;
import com.jobgoalin.workinfo.resume.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final ResumeRepository resumeRepository;

    // 전체 이력서 조회
    public List<Resume> findAllPosts() {
        return resumeRepository.findAll();
    }
}



