package com.jobgoalin.workinfo.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
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

    /**
     * 회원 정보 수정 화면 요청
     */
    @GetMapping("update-form")
    public String updateForm(Model model,HttpSession session) {


        User checkSesssionUser = (User)session.getAttribute("sessionUser");
        if (checkSesssionUser != null) {
            log.info("sessionUser 값 확인 : {}", checkSesssionUser.toString());
        }

        User user = userService.findById(1L);

        model.addAttribute("userInfo", user);

        return "update-form";
    }

    /**
     * 회원 수정 기능 요청
     */

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id, UserRequest.UpdateDTO reqDTO, HttpSession session) {
        reqDTO.validate();
        User user = (User)session.getAttribute("sessionUser");
        User updateUser = userService.updateById(id,reqDTO);
        session.setAttribute("sessionUser",updateUser);
        return "redirect:/update-form";
    }

}
