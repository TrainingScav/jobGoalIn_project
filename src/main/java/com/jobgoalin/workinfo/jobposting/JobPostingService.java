package com.jobgoalin.workinfo.jobposting;

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
        return jobPostingRepository.findAll();
    }

    public JobPostingBoard findById(Long recruitId) {
        return jobPostingRepository.findByRecruitId(recruitId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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

