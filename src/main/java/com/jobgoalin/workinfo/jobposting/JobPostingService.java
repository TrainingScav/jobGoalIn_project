package com.jobgoalin.workinfo.jobposting;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

    public void save(JobPostingBoard jobPostingBoard) {
        jobPostingRepository.save(jobPostingBoard);
    }

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

    public void delete(Long recruitId) {
        jobPostingRepository.deleteById(recruitId);
    }
}

