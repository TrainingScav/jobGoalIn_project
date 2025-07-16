package com.jobgoalin.workinfo.jobposting;

import com.jobgoalin.workinfo._core.errors.exception.Exception404;
import com.jobgoalin.workinfo.resume.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    public List<JobPostingBoard> findAll() {
        List<JobPostingBoard> boards = jobPostingRepository.findAll();
        if (boards.isEmpty()) {
            throw new Exception404("공고 목록이 없습니다");
        }
        return boards;
        // return jobPostingRepository.findAll();
    }

    public JobPostingBoard findById(Long recruitId) {
        return jobPostingRepository.findByRecruitId(recruitId)
                .orElseThrow(() -> new Exception404("해당 게시물을 찾을 수 없습니다"));
    }

    @Transactional
    public void save(JobPostingBoard jobPostingBoard) {
        jobPostingRepository.save(jobPostingBoard);
    }

    @Transactional
    public void update(Long recruitId, JobPostingBoard updated) {
        JobPostingBoard existing = findById(recruitId);
        existing.setTitle(updated.getTitle());
        existing.setCompanyName(updated.getCompanyName());
        existing.setContent(updated.getContent());
        existing.setLocation(updated.getLocation());
        existing.setEmploymentType(updated.getEmploymentType());
        existing.setRequireCareerYears(updated.getRequireCareerYears());
        existing.setDeadline(updated.getDeadline());
        jobPostingRepository.save(existing);
    }

    @Transactional
    public void delete(Long recruitId) {
        jobPostingRepository.deleteById(recruitId);
    }

    public List<JobPostingBoard> findJobPostingsByUserId(Long companyId) {
        return jobPostingRepository.findByCompanyId(companyId);
    }
}

