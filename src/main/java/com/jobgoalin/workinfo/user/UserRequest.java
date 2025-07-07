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
        private String userNickname;
        private String userCivilSerial;
    }

    @Data
    public static class LoginDTO {
        private String userLoginId;
        private String userPassWord;
    }

    // 회원 정보 수정용 DTO
    @Data
    public static class UpdateDTO {
        private String userPassword;
        private String userNickname;
        private String userAddress;
        private String userPhoneNumber;


        public void validate() {

            if (userPassword == null || userPassword.trim().isEmpty()) {
                throw new IllegalArgumentException("비밀번호는 필수입니다");
            }
            if (userPassword.length() < 4) {
                throw new IllegalArgumentException("비밀번호는 4자 이상이어야 합니다");
            }

            // 닉네임: 필수
            if (userNickname == null || userNickname.trim().isEmpty()) {
                throw new IllegalArgumentException("닉네임은 필수입니다");
            }

            // 휴대폰 번호: 숫자만, 길이 10~11자
            if (userPhoneNumber != null && !userPhoneNumber.trim().isEmpty()) {
                String digitsOnly = userPhoneNumber.replaceAll("[^0-9]", "");// 숫자만 추출 (정규식 최소 사용)
                if (digitsOnly.length() < 10 || digitsOnly.length() > 11) {
                    throw new IllegalArgumentException("올바른 휴대폰 번호 형식이 아닙니다 (숫자 10~11자리)");
                }
                for (char c : digitsOnly.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new IllegalArgumentException("휴대폰 번호는 숫자만 입력해야 합니다");
                    }
                }
            }
            // 주소: 100자 이하
            if (userAddress != null && userAddress.length() > 100) {
                throw new IllegalArgumentException("주소는 100자 이하로 입력해주세요");
            }
        }
    }
}
