package com.jobgoalin.workinfo.UserInfo;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor // DI 처리
@Controller
public class MainPageInfo {

    private static final Logger log = LoggerFactory.getLogger(MainPageInfo.class);

    @GetMapping("/")
    public String index() {
        return "login";
    }
}
