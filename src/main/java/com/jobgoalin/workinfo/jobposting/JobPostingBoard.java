package com.jobgoalin.workinfo.jobposting;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "recruit_info")
@Entity
public class JobPostingBoard {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recruitId;

    private Long companyId;

    private String title;

    private String companyName;

    private String content;

    private String requireCareerYears;

    private String employmentType;

    private String location;

    private String instId;

    @CreationTimestamp
    private Timestamp instDate;

    private LocalDate postedAt;
    private LocalDate deadline;

    @Builder
    public JobPostingBoard(Long recruitId, Long companyId, String title, String companyName, String content, String requireCareerYears,
                           String employmentType, String location, String instId,
                           LocalDate postedAt, LocalDate deadline, Timestamp instDate) {
        this.recruitId = recruitId;
        this.companyId = companyId;
        this.title = title;
        this.companyName = companyName;
        this.content = content;
        this.requireCareerYears = requireCareerYears;
        this.employmentType = employmentType;
        this.location = location;
        this.instId= instId;
        this.postedAt = postedAt;
        this.deadline = deadline;
        this.instDate = instDate;
    }



}

