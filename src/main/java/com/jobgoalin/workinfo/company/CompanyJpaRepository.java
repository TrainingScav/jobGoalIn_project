package com.jobgoalin.workinfo.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyJpaRepository extends JpaRepository<CompanyInfo, Long> {

    Page<CompanyInfo> findAll(Pageable pageable);

    @Query("SELECT c FROM CompanyInfo c WHERE c.companyName LIKE %:keyword%")
    Page<CompanyInfo> findCompanyNamesByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT ci FROM CompanyInfo ci WHERE ci.compUser.Id = :id")
    CompanyInfo findCompanyInfoByCompUserId(@Param("id")Long id);
}
