package com.jobgoalin.workinfo.resumelist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeListRepository extends JpaRepository<ResumeListBoard, Integer> {
    Optional<ResumeListBoard> findById(int resumeNo);

}
