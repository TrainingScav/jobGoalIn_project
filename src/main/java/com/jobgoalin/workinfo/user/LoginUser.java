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
    private boolean isCompany; // 기업회원 여부
    private boolean isCompanyInfoWriteYn; // 기업정보 작성 가능 여부
    private boolean isCompanyInfoUpdateAndDeleteYn; // 기업정보 수정/삭제 가능 여부

    public LoginUser(Long id, String name, String loginId, String userNickname, boolean isCompany, boolean isCompanyInfoWriteYn, boolean isCompanyInfoUpdateAndDeleteYn) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.userNickname = userNickname;
        this.isCompany = isCompany;
        this.isCompanyInfoWriteYn = isCompanyInfoWriteYn;
        this.isCompanyInfoUpdateAndDeleteYn = isCompanyInfoUpdateAndDeleteYn;
    }
}
