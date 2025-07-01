package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo.info.CompanyInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping("/company/list")
    public String companyInfoList(Model model) {

        List<CompanyInfo> companyInfoList = companyService.findAllCompanyInfo();

        log.info("companyInfoList : {}", companyInfoList);

        model.addAttribute("companyInfoList", companyInfoList);
        return "company/company_list";
    }

    @GetMapping("/company/{id}")
    public String companyInfoDetail(@PathVariable(name = "id") Long id, Model model) {

        try {
            CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);
            model.addAttribute("companyInfo", companyInfoDetail);
            return "company/company_detail";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
