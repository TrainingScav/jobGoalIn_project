package com.jobgoalin.workinfo.info;

import com.jobgoalin.workinfo.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "user_skill_list")
@Entity
public class UserSkillList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userSkillListNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래키 컬럼명 명시
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id") // 외래키 컬럼명 명시
    private SkillList skillList;

    private String instId;

    @CreationTimestamp
    private Timestamp instDate;

}
