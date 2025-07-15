package com.jobgoalin.workinfo.community;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findAll(Pageable pageable);
}