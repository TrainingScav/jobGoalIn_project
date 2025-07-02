package com.jobgoalin.workinfo.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String userLoginId;
        private String userPassWord;
        private String userEmail;
        private String userAddress;
        private String userPhone;
        private String userBirth;
        private String userGender;
        private String userNickName;
        private String userCivilSerial;
    }

    @Data
    public static class LoginDTO {
        private String userLoginId;
        private String userPassWord;
    }
}
