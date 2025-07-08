package com.jobgoalin.workinfo.info;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "access_info")
public class AccessInfo {

    @Id
    private Long accessLevel;

    private String accessDesc;
    private String instId;
    @CreationTimestamp
    private Timestamp instDate;

}
