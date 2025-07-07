package com.jobgoalin.workinfo.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {

    private Long id; // User id 또는 CompUser id
    private String name; // 공통 출력용 이름
    private String loginId; // 로그인 아이디
    private String userNickname; // 유저 닉네임
    private boolean isCompany; // 기업 여부

    public LoginUser(Long id, String name, String loginId, boolean isCompany) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.isCompany = isCompany;
    }
}
