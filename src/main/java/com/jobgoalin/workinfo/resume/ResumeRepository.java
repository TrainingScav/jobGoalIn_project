package com.jobgoalin.workinfo.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> findByUserUserId(Long userId);

    @Query("SELECT r FROM Resume r WHERE r.user.userId = :userId")
    List<Resume> findResumeInfoByUserId(@Param("userId") Long userId);
}
