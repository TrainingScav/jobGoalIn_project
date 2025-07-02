package com.jobgoalin.workinfo.company;

import com.jobgoalin.workinfo._core.errors.exception.Exception500;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompanyController {


    private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyService companyService;

    @GetMapping("/company/list")
    public String companyInfoList(Model model) {

        log.info(">> 기업정보 목록 조회 시작 << ");

        List<CompanyInfo> companyInfoList = companyService.findAllCompanyInfo();
        model.addAttribute("companyInfoList", companyInfoList);

        return "company/company_list";
    }

    @GetMapping("/company/{id}")
    public String companyInfoDetail(@PathVariable(name = "id") Long id, Model model) {

        log.info(">> 기업 상세정보 화면이동 및 조회 시작 << ");

        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);
        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_detail";
    }

    @GetMapping("/company/form")
    public String companyInfoForm() {

        log.info(">> 기업정보 작성화면 이동 << ");

        return "company/company_form";
    }

    @PostMapping("/company/form")
    public String companyInfoInsert(CompanyRequest.SaveDTO saveDTO) {

        log.info(">> 기업정보 등록 시작 << ");

        CompanyInfo companyInfo = companyService.companyInfoInsert(saveDTO.toEntity());

        if (companyInfo == null) {
            throw new Exception500("등록 처리 중 에러가 발생했습니다.");
        }

        return "redirect:/company/list";
    }

    @GetMapping("/company/{id}/update")
    public String companyInfoUpdateForm(@PathVariable(name = "id") Long id, Model model) {

        log.info(">> 기업정보 수정 화면이동 및 조회 시작 << ");

        CompanyInfo companyInfoDetail = companyService.findCompanyInfoById(id);
        model.addAttribute("companyInfo", companyInfoDetail);

        return "company/company_update";
    }

    @PostMapping("/company/{id}/update")
    public String companyInfoUpdate(@PathVariable(name = "id") Long id, CompanyRequest.SaveDTO saveDTO) {

        log.info(">> 기업정보 수정 시작 << ");

        CompanyInfo companyInfo = companyService.companyInfoUpdate(id, saveDTO.toEntity());
        if (companyInfo == null) {
            throw new Exception500("수정 처리 중 에러가 발생했습니다.");
        }

        return "redirect:/company/"+id;
    }

    @PostMapping("/company/{id}/delete")
    public String companyInfoDelete(@PathVariable(name = "id") Long id) {

        log.info(">> 기업정보 삭제 시작 << ");

        companyService.companyInfoDelete(id);
        return "redirect:/company/list";
    }

}
