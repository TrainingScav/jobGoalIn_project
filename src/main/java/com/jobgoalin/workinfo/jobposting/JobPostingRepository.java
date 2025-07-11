package com.jobgoalin.workinfo.jobposting;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface JobPostingRepository extends JpaRepository<JobPostingBoard, Long> {

    Optional<JobPostingBoard> findByRecruitId(Long recruitId);

    List<JobPostingBoard> findByCompanyId(Long companyId);
}

