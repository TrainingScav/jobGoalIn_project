package com.jobgoalin.workinfo.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyInfoJpaRepository extends JpaRepository<CompanyInfo, Long> {

}
