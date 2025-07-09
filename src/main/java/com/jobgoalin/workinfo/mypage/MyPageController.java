package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.LoginUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MyPageController {


    private final MyPageService myPageService;


    @GetMapping("/user/my-page")
    public String myPage(Model model, HttpSession session) {
        LoginUser loginUser = (LoginUser) session.getAttribute("sessionUser");
        Long userId = loginUser.getId();
        List<SkillList> skills = myPageService.getSkillsByUserId(userId);
        model.addAttribute("skillList",skills);
        return "user/my-page";
    }


    @GetMapping("/resume-list")
    public String resume() {
        return "resume-list";
    }


}
