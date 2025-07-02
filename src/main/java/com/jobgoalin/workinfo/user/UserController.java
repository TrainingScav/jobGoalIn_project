package com.jobgoalin.workinfo.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 화면 요청
    @GetMapping("/join")
    public String joinForm() {
        return "user/join-form"; // templates/user/join-form.html
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO dto, Model model) {
        try {
            userService.join(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "user/join-form";
        }
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO dto, HttpSession session, Model model) {
        User user = userService.login(dto);
        if (user != null) {
            session.setAttribute("loginUser", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "user/login-form";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
