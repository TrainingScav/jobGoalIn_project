package com.jobgoalin.workinfo.info;

import com.jobgoalin.workinfo.user.UserSkillList;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "skill_list")
@Entity
public class SkillList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillListNo;

    @Column(nullable = false)
    private Long skillGrpId;

    @Column(unique = true, nullable = false)
    private Long skillId;

    @Column(nullable = false)
    private String skillName;

    private String skillDesc;
    private String instId;

    @OneToMany(mappedBy = "skillList")
    private List<UserSkillList> userSkills;

    @CreationTimestamp
    private Timestamp instDate;

    @Builder

    public SkillList(Long skillListNo, Long skillGrpId, Long skillId, String skillName, String skillDesc, String instId, List<UserSkillList> userSkills, Timestamp instDate) {
        this.skillListNo = skillListNo;
        this.skillGrpId = skillGrpId;
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillDesc = skillDesc;
        this.instId = instId;
        this.userSkills = userSkills;
        this.instDate = instDate;
    }
}
