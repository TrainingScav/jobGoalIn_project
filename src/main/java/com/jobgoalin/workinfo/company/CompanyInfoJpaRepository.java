package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.user.CompUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompanyInfoJpaRepository extends JpaRepository<CompanyInfo, Long> {

    /**/
    @Query("SELECT ci FROM CompanyInfo ci WHERE ci.compUser.Id = :id")
    CompanyInfo findCompanyInfoByCompUserId(@Param("id")Long id);


}
