package com.jobgoalin.workinfo.info;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.w3c.dom.Text;

import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

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
}
