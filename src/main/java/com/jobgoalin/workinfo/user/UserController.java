package com.jobgoalin.workinfo.user;

import com.jobgoalin.workinfo._core.errors.exception.Exception400;
import com.jobgoalin.workinfo._core.errors.exception.Exception401;
import com.jobgoalin.workinfo._core.errors.exception.Exception500;
import com.jobgoalin.workinfo.resume.Resume;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
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

        // request 값 유효성 확인
        dto.validate();

        // 회원가입 진행
        userService.join(dto);

        return "redirect:/login";
    }

    // 기업 이용자 회원가입 화면 요청
    @GetMapping("/signup/company")
    public String companyUserSignupForm() {
        return "user/compUserJoin"; // templates/user/join-form.html
    }

    // 기업 이용자 회원가입 요청
    @PostMapping("/signup/company")
    public String join(UserRequest.CompJoinDTO dto) {

        // request 값 유효성 확인
        dto.validate();

        // 회원가입 진행
        userService.compJoin(dto);

        return "redirect:/login";
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginForm() {
        log.info("로그인 요청 폼");
        return "user/login";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO dto, HttpSession session, Model model) {
        log.info("=== 로그인 시도 ===");
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
    public String updateForm(Model model, HttpSession session, UserRequest.UpdateDTO reqDTO) {

        LoginUser userId = (LoginUser)session.getAttribute("sessionUser");
        User user = userService.findById(userId.getId());
        model.addAttribute("userInfo", user);

        return "/user/update-form";
    }

    /**
     * 회원 수정 기능 요청
     */

    @PostMapping("/user/update-form")
    public String update(UserRequest.UpdateDTO reqDTO, HttpSession session) {

        reqDTO.validate();

        LoginUser loginUser = (LoginUser) session.getAttribute("sessionUser");
        reqDTO.setUserId(loginUser.getId());

        if (loginUser.isCompany()) { //기업 회원일 시
            // 기업 회원 업데이트 서비스 로직 호출
        } else {
            User updateUser = userService.updateById(reqDTO);

            loginUser.setUserNickname(updateUser.getUserNickName());
        }

        session.setAttribute("sessionUser",loginUser);
        return "redirect:/user/update-form";
    }

    // 오시는길 화면 이동
    @GetMapping("/contactus")
    public String contactUs() {
        return "contactUs";
    }

}
