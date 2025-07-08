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

    // 메인 페이지 진입
    @GetMapping("/")
    public String index(HttpSession session) {

        LoginUser user = (LoginUser) session.getAttribute("sessionUser");

        if (user != null) {
            log.info("sessionUser 값 확인 : {}", user );
        }

        return "index";
    }

    // 일반 이용자 회원가입 화면 요청
    @GetMapping("/signup/normal")
    public String normalUserSignupForm() {
        return "user/userJoin"; // templates/user/join-form.html
    }

    // 일반 이용자 회원가입 요청
    @PostMapping("/signup/normal")
    public String normalUserSignup(UserRequest.JoinDTO dto, Model model) {
        try {
            userService.join(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "user/userJoin";
        }
    }

    // 기업 이용자 회원가입 화면 요청
    @GetMapping("/signup/company")
    public String companyUserSignupForm() {
        return "user/compUserJoin"; // templates/user/join-form.html
    }

    // 기업 이용자 회원가입 요청
    @PostMapping("/signup/company")
    public String join(UserRequest.CompJoinDTO dto, Model model) {
        try {
            userService.compJoin(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "user/compUserJoin";
        }
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO dto, HttpSession session, Model model) {

        log.info("dto 값 확인 : {}", dto.toString());

        if (dto.getLoginType().equals("normal")) {
            User user = userService.login(dto);

            // 로그인 유저 세션정보 저장 (일반)
            LoginUser loginUser = new LoginUser();
            loginUser.setId(user.getUserId());
            loginUser.setName(user.getUsername());
            loginUser.setLoginId(user.getUserLoginId());
            loginUser.setUserNickname(user.getUserNickName());
            loginUser.setCompany(user.getIsCompanyUserYn());

            if (user != null) {
                session.setAttribute("sessionUser", loginUser);
                return "redirect:/";
            } else {
                model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return "user/login";
            }

        } else {
            CompUser compUser = userService.compLogin(dto);


            LoginUser loginUser = new LoginUser();
            loginUser.setId(compUser.getCompUserId());
            loginUser.setName(compUser.getCompUserName());
            loginUser.setLoginId(compUser.getCompUserLoginId());
            loginUser.setUserNickname(compUser.getCompUserNickname());
            loginUser.setCompany(compUser.getIsCompanyUserYn());

            if (compUser != null) {
                session.setAttribute("sessionUser", loginUser);
                return "redirect:/";
            } else {
                model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
                return "user/login";
            }
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
    @GetMapping("/user/update-form")
    public String updateForm(Model model,HttpSession session) {


        User checkSesssionUser = (User)session.getAttribute("sessionUser");
        if (checkSesssionUser != null) {
            log.info("sessionUser 값 확인 : {}", checkSesssionUser.toString());
        }

        User user = userService.findById(1L);

        model.addAttribute("userInfo", user);

        return "/user/update-form";
    }

    /**
     * 회원 수정 기능 요청
     */

    @PostMapping("/user/update-form/{id}")
    public String update(@PathVariable(name = "id") Long id, UserRequest.UpdateDTO reqDTO, HttpSession session) {
        reqDTO.validate();
        User user = (User)session.getAttribute("sessionUser");
        User updateUser = userService.updateById(id,reqDTO);
        session.setAttribute("sessionUser",updateUser);
        return "redirect:/uesr/update-form";
    }

    // 오시는길 화면 이동
    @GetMapping("/contactus")
    public String contactUs() {
        return "contactUs";
    }

}
