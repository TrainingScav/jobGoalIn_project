package com.jobgoalin.workinfo.mypage;

import com.jobgoalin.workinfo.info.SkillList;
import com.jobgoalin.workinfo.user.LoginUser;
import com.jobgoalin.workinfo.user.User;
import com.jobgoalin.workinfo.user.UserRepository;
import com.jobgoalin.workinfo.user.UserSkillList;
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
    private final MyPageService myPageService;


    @GetMapping("/user/my-page")
    public String myPage(Model model, HttpSession session) {

        LoginUser loginUser = (LoginUser) session.getAttribute("sessionUser");
        Long userId = loginUser.getId();

        User user = userRepository.findById(userId).orElse(null);
        List<UserSkillList> skillList = myPageService.getUserSkillsByUserId(userId);

        model.addAttribute("userInfo", user);
        model.addAttribute("userSkillList",skillList);

        return "user/my-page";
    }

    @GetMapping("/resume-list")
    public String resume() {
        return "resume-list";
    }
}
