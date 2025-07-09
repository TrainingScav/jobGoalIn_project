package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.UserSkillList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillListJpaRepository extends JpaRepository<UserSkillList, Long> {
    List<UserSkillList> findByUser_UserId(Long userId);
}
