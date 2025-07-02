package com.jobgoalin.workinfo.company;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(nullable = false)
    private String companyName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String companyDesc;
    @Column(nullable = false)
    private String companyCeoName;
    private String homepageUrl;
    private String phoneNumber;
    private String companyEmail;
    private String companyAddress;

    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

    @Builder
    public CompanyInfo(Long companyId, String companyName, String companyDesc, String companyCeoName, String homepageUrl, String phoneNumber, String companyEmail, String companyAddress, String instId, Timestamp instDate) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyDesc = companyDesc;
        this.companyCeoName = companyCeoName;
        this.homepageUrl = homepageUrl;
        this.phoneNumber = phoneNumber;
        this.companyEmail = companyEmail;
        this.companyAddress = companyAddress;
        this.instId = instId;
        this.instDate = instDate;
    }
}
