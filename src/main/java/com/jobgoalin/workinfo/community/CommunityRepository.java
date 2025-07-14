package com.jobgoalin.workinfo.community;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByPostId(Long postId);
}