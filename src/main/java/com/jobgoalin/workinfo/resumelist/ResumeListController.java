package com.jobgoalin.workinfo.resumelist;

import com.jobgoalin.workinfo.resume.Resume;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ResumeListController {

    private static final Logger log = LoggerFactory.getLogger(ResumeListController.class);
    private final ResumeListService resumeListService;

    @GetMapping("/resumes")
    public String showResumeList(Model model) {


        model.addAttribute("resumeList", resumeListService.getAllResumes());
        return "resumelist/resume_list";
    }

    @GetMapping("/resumes/{resumeNo}")
    public String getResumeDetail(@PathVariable Long resumeNo, Model model) {
        Resume resume = resumeListService.findById(resumeNo);

        resume.getUserSkillLists().forEach(userSkillList -> {
            if (userSkillList.getSkillList().getSkillId() <= 6) {
                resume.setPosition(userSkillList.getSkillList().getSkillName());
            } else {
                resume.setSkill(userSkillList.getSkillList().getSkillName());
            }
        });

        model.addAttribute("resume", resume);
        return "resumelist/resume_detail";
    }

}



