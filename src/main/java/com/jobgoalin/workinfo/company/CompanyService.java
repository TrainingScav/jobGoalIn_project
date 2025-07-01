package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.info.CompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyJpaRepository companyJpaRepository;

    public List<CompanyInfo> findAllCompanyInfo() {

        List<CompanyInfo> CompanyList = companyJpaRepository.findAll();

        return CompanyList;
    }

    public CompanyInfo findCompanyInfoById(Long id) throws Exception {

        CompanyInfo companyInfo = companyJpaRepository.findById(id).orElseThrow(() -> {
            return new Exception("해당 게시물이 존재하지 않습니다.");
        });

        return companyInfo;
    }
}
