package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo._core.errors.exception.Exception403;
import com.jobgoalin.workinfo._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyService.class);
    private final CompanyJpaRepository companyJpaRepository;

    public List<CompanyInfo> findAllCompanyInfo() {

        List<CompanyInfo> CompanyList = companyJpaRepository.findAll();

        return CompanyList;
    }

    public CompanyInfo findCompanyInfoById(Long id) {

        CompanyInfo companyInfo = companyJpaRepository.findById(id).orElseThrow(() ->
            new Exception404("해당 게시물이 존재하지 않습니다.")
        );

        return companyInfo;
    }

    @Transactional
    public CompanyInfo companyInfoInsert(CompanyInfo companyInfo) {

        CompanyInfo savedCompanyInfo = companyJpaRepository.save(companyInfo);

        return savedCompanyInfo;
    }

    @Transactional
    public CompanyInfo companyInfoUpdate(Long id, CompanyInfo companyInfo) {

        CompanyInfo updateCompanyInfo = findCompanyInfoById(id);

        // 더티체킹 사용.
        updateCompanyInfo.setCompanyName(companyInfo.getCompanyName());
        updateCompanyInfo.setCompanyDesc(companyInfo.getCompanyDesc());
        updateCompanyInfo.setCompanyCeoName(companyInfo.getCompanyCeoName());
        updateCompanyInfo.setHomepageUrl(companyInfo.getHomepageUrl());
        updateCompanyInfo.setPhoneNumber(companyInfo.getPhoneNumber());
        updateCompanyInfo.setCompanyEmail(companyInfo.getCompanyEmail());
        updateCompanyInfo.setCompanyAddress(companyInfo.getCompanyAddress());

        return updateCompanyInfo;
    }

    @Transactional
    public void companyInfoDelete(Long id) {

        log.info("게시글 삭제 서비스 시작 - ID {}", id);
        CompanyInfo companyInfo = companyJpaRepository.findById(id).orElseThrow(() ->
            new Exception404("삭제하려는 게시글이 없습니다")
        );

        companyJpaRepository.deleteById(id);
    }
}
