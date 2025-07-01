package com.jobgoalin.workinfo.mypage;

import org.springframework.web.bind.annotation.GetMapping;

public class MyPageController {

    @GetMapping("/")
    public String updateUserInfo() {
        return "filelist";
    }
}
