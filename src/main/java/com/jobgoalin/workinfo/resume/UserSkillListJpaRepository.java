package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.UserSkillList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSkillListJpaRepository extends JpaRepository<UserSkillList, Long> {
    List<UserSkillList> findByUser_UserId(Long userId);
    @Query("select usl from UserSkillList usl join fetch usl.skillList where usl.user.userId = :userId")
    List<UserSkillList> findByUserWithSkillFetch(@Param("userId") Long userId);
}
