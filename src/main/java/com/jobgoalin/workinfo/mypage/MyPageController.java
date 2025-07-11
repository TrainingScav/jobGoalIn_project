package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.jobposting.JobPostingBoard;
import com.jobgoalin.workinfo.jobposting.JobPostingRepository;
import com.jobgoalin.workinfo.jobposting.JobPostingService;
import com.jobgoalin.workinfo.resume.Resume;
import com.jobgoalin.workinfo.resume.ResumeRepository;
import com.jobgoalin.workinfo.resume.ResumeService;
import com.jobgoalin.workinfo.user.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final CompUserRepository compUserRepository;
    private final JobPostingRepository jobPostingRepository;

    private final ResumeService resumeService;
    private final JobPostingService jobPostingService;


    @GetMapping("/user/my-page")
    public String myPage(Model model, HttpSession session) {

        LoginUser loginUser = (LoginUser) session.getAttribute("sessionUser");
        Long userId = loginUser.getId();
        if (loginUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("loginUser",loginUser);

        if (loginUser.isCompany()) {
            // 기업회원 데이터 준비

            CompUser compUser = compUserRepository.findById(userId).orElse(null);
            List<JobPostingBoard> myJobPostings = jobPostingService.findJobPostingsByUserId(userId);
            JobPostingBoard jobPostingBoard = jobPostingRepository.findById(userId).orElse(null);

            model.addAttribute("compUserInfo", compUser);
            model.addAttribute("jobPostings",myJobPostings);
            model.addAttribute("jobPostingInfo",jobPostingBoard);
        } else {
            // 일반 회원 데이터 준비

            User user = userRepository.findById(userId).orElse(null);
            List<Resume> myResumes = resumeService.findResumesByUserId(userId);
            Resume resume = resumeRepository.findById(userId).orElse(null);

            model.addAttribute("userInfo", user);
            model.addAttribute("resumeInfo", resume);
            model.addAttribute("resumes", myResumes);
        }
            return "user/my-page";
    }




        @GetMapping("/resume-list")
        public String resume () {
            return "resume-list";
        }

}