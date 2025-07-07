package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    /**
     * 이력서 등록 화면 요청
     */
//    @GetMapping("/resume/new")
//    public String ResumeForm(Long resumeNo,
//                             HttpServletRequest request,HttpSession session, Model model) {
//
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        resumeService.checkResumeOwner(resumeNo,sessionUser.getUserId());
//        request.setAttribute("resume", resumeService.findById(resumeNo));
//        return "resume_register";
//    }

    /**
     * 이력서 등록 화면 요청
     */

    @GetMapping("/resume/new")
    public String ResumeForm() {
        return"resume_register";
    }




    /**
     * 이력서 등록 기능 요청


    @PostMapping("/resume")
    public String createResume() {

    }
     */
}
