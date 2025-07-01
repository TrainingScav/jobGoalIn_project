package com.jobgoalin.workinfo.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @GetMapping("/company/list")
    public String companyInfoList() {

        return "company/company_list";
    }
}
