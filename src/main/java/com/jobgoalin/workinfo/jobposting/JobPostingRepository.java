package com.jobgoalin.workinfo.jobposting;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Optional;


public interface JobPostingRepository extends JpaRepository<JobPostingBoard, Long> {

    Optional<JobPostingBoard> findByRecruitId(Long recruitId);
}

