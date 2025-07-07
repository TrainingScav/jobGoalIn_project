package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserController;
import com.jobgoalin.workinfo.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {



    private final ResumeService resumeService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    /**
     * 이력서 등록 화면 요청
     */

    @GetMapping("/resume")
    public String ResumeForm(Model model,HttpSession session) {

        User checkSessionUser = (User)session.getAttribute("sessionUser");
        if (checkSessionUser != null) {
            log.info("sessionUser 값 확인 : {}",checkSessionUser.toString());
        }

        User user = resumeService.findById(1L);

        model.addAttribute("userInfo",user);

        return"resume-register";
    }

    /**
     * 이력서 등록 기능 요청


    @PostMapping("/resume/register")
    public String registerResume(@SessionAttribute("userId") Long userId, @ModelAttribute ResumeRequest.ResumeRegisterDTO resumeRegisterDTO) {
        resumeRegisterDTO.setUserId(userId);
        resumeService.registerResume(resumeRegisterDTO);
        return"resume-list";
    }
     */
}
