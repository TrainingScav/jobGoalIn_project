package com.jobgoalin.workinfo.resumelist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ResumeListController {

    private final ResumeListService resumeListService;

    @GetMapping("/resumes")
    public String showResumeList(Model model) {
        model.addAttribute("resumeList", resumeListService.getAllResumes());
        return "resumelist/resume_list";
        }

    @GetMapping("/resumes/{resumeNo}")
    public String getResumeDetail(@PathVariable int resumeNo, Model model) {
        ResumeListBoard resume = resumeListService.findById(resumeNo);
        model.addAttribute("resume", resume);
        return "resumelist/resume_detail";
    }

}



