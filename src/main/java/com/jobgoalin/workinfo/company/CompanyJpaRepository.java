package com.jobgoalin.workinfo.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<CompanyInfo, Long> {

}
