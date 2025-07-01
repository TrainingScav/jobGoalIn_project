package com.jobgoalin.workinfo.info;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "legal_dong_info")
@Entity
public class LegalDongInfo {

    @Id
    private Long legalDongCode;

    private String cityProvince;
    private String cityCountryDistrict;
    private String eupMyeonDong;
    private String villageName;
    private int orderNo;

    @CreationTimestamp
    private Timestamp creationDate;
    private String instId;
    @CreationTimestamp
    private Timestamp instDate;
}
