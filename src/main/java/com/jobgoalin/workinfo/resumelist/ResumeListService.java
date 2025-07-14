package com.jobgoalin.workinfo.resumelist;

import com.jobgoalin.workinfo.resume.Resume;
import com.jobgoalin.workinfo.resume.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeListService {

    private final ResumeRepository resumeRepository;

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Resume findById(Long resumeNo) {
        return resumeRepository.findById(resumeNo)
                .orElseThrow(() -> new IllegalArgumentException("이력서를 찾을 수 없습니다. resumeNo=" + resumeNo));
    }

}
