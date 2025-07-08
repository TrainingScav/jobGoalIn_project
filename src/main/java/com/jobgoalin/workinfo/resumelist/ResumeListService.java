package com.jobgoalin.workinfo.resumelist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeListService {

    private final ResumeListRepository resumeListRepository;

    public List<ResumeListBoard> getAllResumes() {
        return resumeListRepository.findAll();
    }

    public ResumeListBoard findById(int resumeNo) {
        return resumeListRepository.findById(resumeNo)
                .orElseThrow(() -> new IllegalArgumentException("이력서를 찾을 수 없습니다. resumeNo=" + resumeNo));
    }

}
