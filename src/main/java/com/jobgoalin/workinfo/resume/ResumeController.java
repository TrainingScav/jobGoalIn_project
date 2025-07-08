package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.LoginUser;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserController;
import com.jobgoalin.workinfo.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;
    private static final Logger log = LoggerFactory.getLogger(ResumeController.class);


    /**
     * 이력서 등록 화면 요청
     */


    @GetMapping("/resumelist/resume-register")
    public String ResumeForm(Model model,HttpSession session) {

        LoginUser checkSessionUser = (LoginUser) session.getAttribute("sessionUser");
        if (checkSessionUser != null) {
            log.info("sessionUser 값 확인 : {}", checkSessionUser.toString());
        }
        Resume resume = resumeService.findById(1L);
        model.addAttribute("resumeInfo", resume);
        return "resumelist/resume-register";
    }

//    @GetMapping("/resume-register")
//    public String ResumeForm() {
//    public String ResumeForm(Model model,HttpSession session) {
//        User checkSessionUser = (User)session.getAttribute("sessionUser");
//        if (checkSessionUser != null) {
//            log.info("sessionUser 값 확인 : {}",checkSessionUser.toString());
//        }
//
//        User user = resumeService.findById(1L);
//
//        model.addAttribute("userInfo",user);
//        model.addAttribute("resumeInfo",new  ResumeRequest.ResumeRegisterDTO());
//        return "resumelist/resume-register";
//    }

    /**
     * 이력서 등록 기능 요청
    **/

//    @PostMapping("/resume-register")
//    public String registerResume(@PathVariable Long userId,
//                                 UserRequest.Uesr) {
//        resumeRegisterDTO.setUserId(userId);
//        resumeService.registerResume(resumeRegisterDTO);
//        return "redirect:/resumelist/resume-register";
//    }
}
