package com.jobgoalin.workinfo.mypage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class MyPageController {


    // 개인 정보 수정


    @GetMapping("/my-page")
    public String myPage() {
        return "my-page";
    }


    @GetMapping("/resume-list")
    public String resume() {
        return "resume-list";
    }
}
