package com.jobgoalin.workinfo.jobposting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPostingBoard, Long> {

    Optional<JobPostingBoard> findByRecruitId(Long recruitId);
}

