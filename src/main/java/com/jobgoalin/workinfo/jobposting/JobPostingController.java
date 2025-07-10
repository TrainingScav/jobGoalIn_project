package com.jobgoalin.workinfo.jobposting;

import com.jobgoalin.workinfo.company.CompanyInfo;
import com.jobgoalin.workinfo.company.CompanyService;
import com.jobgoalin.workinfo.resumelist.ResumeListController;
import com.jobgoalin.workinfo.user.LoginUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class JobPostingController {

    private static final Logger log = LoggerFactory.getLogger(JobPostingController.class);
    private final JobPostingService jobPostingService;
    private final CompanyService companyService;

    @GetMapping("/jobpostingboard")
    public String boardList(Model model, HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");

        if (user != null) {
            // 기업정보가 등록 되어 있는지 확인.
            CompanyInfo searchCompanyInfo = companyService.findCompanyInfoByUserId(user.getId());

            // 기업정보가 등록 되어 있으면 채용공고 작성 가능
            if (searchCompanyInfo != null) {
                model.addAttribute("isJobPostWritable", true);
            }
        }

        model.addAttribute("jobPostingBoardList", jobPostingService.findAll());
        return "board/jobPosting";
    }

    @GetMapping("/jobpostingboard/detail/{recruitId}")
    public String detail(@PathVariable Long recruitId, Model model, HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");

        JobPostingBoard jobPostingBoard = jobPostingService.findById(recruitId);

        if (user != null) {

            CompanyInfo companyInfo = companyService.findCompanyInfoByUserId(user.getId());

            // 수정/삭제 버튼 표시여부 (등록한 기업회원이랑 같은 id인지 확인)
            if (companyInfo != null && jobPostingBoard.getCompanyId().equals(companyInfo.getCompanyId())) {
                model.addAttribute("isJobPostingInfoUpdateAndDeleteYn", true);
            }
        }

        model.addAttribute("jobPostingBoard", jobPostingBoard);
        return "board/detail";
    }

    @GetMapping("/jobpostingboard/write")
    public String showWriteForm() {


        return "board/save-form";
    }

    @PostMapping("/posting/write")
    public String submitJobPosting(JobPostingRequest.SaveDTO saveDTO, HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompanyInfo companyInfo = companyService.findCompanyInfoByUserId(user.getId());

        // 넘어온 값 검증
        saveDTO.validate();

        jobPostingService.save(saveDTO.toEntity(companyInfo));
        return "redirect:/jobpostingboard";
    }

    @GetMapping("/jobpostingboard/{recruitId}/update-form")
    public String updateForm(@PathVariable Long recruitId, Model model) {
        model.addAttribute("jobPostingBoard", jobPostingService.findById(recruitId));
        return "board/update-form";
    }

    @PostMapping("/jobpostingboard/{recruitId}/update")
    public String update(@PathVariable Long recruitId, JobPostingRequest.UpdateDTO updateDTO, HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");
        CompanyInfo companyInfo = companyService.findCompanyInfoByUserId(user.getId());

        // 넘어온 값 검증
        updateDTO.validate();

        jobPostingService.update(recruitId, updateDTO.toEntity(companyInfo));
        return "redirect:/jobpostingboard/detail/" + recruitId;
    }

    @PostMapping("/jobpostingboard/{recruitId}/delete")
    public String delete(@PathVariable Long recruitId) {
        jobPostingService.delete(recruitId);
        return "redirect:/jobpostingboard";
    }

}










