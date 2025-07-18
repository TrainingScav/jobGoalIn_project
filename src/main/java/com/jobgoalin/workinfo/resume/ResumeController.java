package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.LoginUser;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserService;
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
public class ResumeController {

    private static final Logger log = LoggerFactory.getLogger(ResumeController.class);

    private final ResumeService resumeService;
    private final UserService userService;

    // 이력서 등록 화면 요청
    @GetMapping("/resumelist/resume-register")
    public String ResumeForm() {
        return "resumelist/resume-register";
    }

    // 이력서 등록 기능 요청
    @PostMapping("/resume-register")
    public String registerResume(HttpSession session, ResumeRequest.ResumeRegisterDTO resumeRegisterDTO) {

        log.info("dto 값 확인 : {}", resumeRegisterDTO.toString());
        LoginUser sessionUser = (LoginUser) session.getAttribute("sessionUser");

        resumeRegisterDTO.setUserId(sessionUser.getId());
        resumeRegisterDTO.setUserNickname(sessionUser.getUserNickname());

        resumeService.save(resumeRegisterDTO);

        return "redirect:/";
    }

    // 이력서 수정 화면
    @GetMapping("/resumelist/resume-update/{id}")
    public String resumeUpdateForm(@PathVariable(name = "id") Long id, Model model) {
        Resume resume = resumeService.getResumeById(id);
        model.addAttribute("resumeInfo", resume);
        return "resumelist/resume-update";
    }

    // 이력서 수정 기능 요청
    @PostMapping("/resumelist/resume-update/{id}")
    public String resumeUpdate(@PathVariable(name = "id")Long id,ResumeRequest.UpdateDTO updateDTO,HttpSession session) {
        log.info(" 이력서 수정 시작 ");
        LoginUser loginUser = (LoginUser)session.getAttribute("sessionUser");
        Resume updateresume = resumeService.update(id,updateDTO);
     return "redirect:/user/my-page";
    }
}
