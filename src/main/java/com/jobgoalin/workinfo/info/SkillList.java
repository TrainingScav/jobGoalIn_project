package com.jobgoalin.workinfo.info;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "skill_list")
@Entity
public class SkillList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skillListNo;

    @Column(nullable = false)
    private int skillGrpId;

    @Column(unique = true, nullable = false)
    private int skillId;

    @Column(nullable = false)
    private String skillName;

    private String skillDesc;
    private String instId;

    @CreationTimestamp
    private Timestamp instDate;
}
