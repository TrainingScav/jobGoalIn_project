package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.info.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyJpaRepository extends JpaRepository<CompanyInfo, Long> {
}
