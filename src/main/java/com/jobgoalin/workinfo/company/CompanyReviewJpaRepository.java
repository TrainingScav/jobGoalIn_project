package com.jobgoalin.workinfo.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyReviewJpaRepository extends JpaRepository<CompanyReview, Long> {

    @Query("SELECT cr FROM CompanyReview cr JOIN FETCH cr.companyInfo ci WHERE ci.companyId = :id")
    Page<CompanyReview> findReviewsJoinCompanyInfo(Pageable pageable, @Param("id") Long id);

    @Query("SELECT cr FROM CompanyReview cr JOIN FETCH cr.companyInfo ci WHERE ci.companyId = :id")
    List<CompanyReview> findReviewsJoinCompanyInfo(@Param("id") Long id);

    @Query("SELECT COUNT(cr) FROM CompanyReview cr WHERE cr.user.id = :id")
    Long countByUserId(@Param("id") Long id);

}
