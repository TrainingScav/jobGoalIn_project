package com.jobgoalin.workinfo.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class MyPageController {


    @GetMapping("/my_page")
    public String myPage() {
        return "my_page";
    }

    // http://localhost:8080/filelist

    @GetMapping("/edit_profile")
    public String updateUserInfo() {
        return "edit_profile";
    }

    @GetMapping("/resume_list")
    public String resume() {
        return "resume_list";
    }
}
