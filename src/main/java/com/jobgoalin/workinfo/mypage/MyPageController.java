package com.jobgoalin.workinfo.mypage;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class MyPageController {


    // 개인 정보 수정


    @GetMapping("/user/my-page")
    public String myPage(Model model, HttpSession session) {




        return "user/my-page";
    }


    @GetMapping("/resume-list")
    public String resume() {
        return "resume-list";
    }


}
