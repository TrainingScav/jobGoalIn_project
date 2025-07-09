package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.info.SkillList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkillListJpaRepository extends JpaRepository<SkillList, Long> {

    @Query("SELECT sl FROM SkillList sl WHERE sl.skillId = :skillId")
    SkillList findBySkillId(@Param("skillId") Long skillId);
}
