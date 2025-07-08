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
        private String userPhoneNumber;
        private String userBirth;
        private String userGender;
        private String userNickname;
        private String userCivilSerial;

        public void validate() {

            // 이름 : 필수입력, 숫자 및 특수문자 제한
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("이름은 필수입니다");
            }
            if (!username.matches("^[가-힣a-zA-Z]{2,20}$")) {
                throw new IllegalArgumentException("이름은 한글,영문만 입력 가능하며 2~20자 이내로 해주세요");
            }

            // 로그인 아이디 : 길이 제한 4~20, 영문/숫자만
            if (userLoginId == null || userLoginId.trim().isEmpty()) {
                throw new IllegalArgumentException("로그인 아이디는 필수입니다");
            }
            if (!userLoginId.matches("^[a-zA-Z0-9]{4,20}$")) {
                throw new IllegalArgumentException("로그인 아이디는 영문, 숫자 조합 4~20자 이내로 해주세요.");
            }
            // DB 중복 체크는 서비스에서 따로 처리

            // 비밀번호 : 필수 입력, 4자 이상
            if (userPassWord == null || userPassWord.trim().isEmpty()) {
                throw new IllegalArgumentException("비밀번호는 필수입니다");
            }
            if (userPassWord.length() < 4) {
                throw new IllegalArgumentException("비밀번호는 4자 이상이어야 합니다");
            }

            // 이메일 : 필수 입력, @ 포함 확인
            if (userEmail == null || userEmail.trim().isEmpty()) {
                throw new IllegalArgumentException("이메일은 필수입니다");
            }
            if (!userEmail.contains("@")) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다");
            }

            // 주소 : 필수 입력, 100자 이하
            if (userAddress == null || userAddress.trim().isEmpty()) {
                throw new IllegalArgumentException("주소는 필수입니다");
            }
            if (userAddress.length() > 100) {
                throw new IllegalArgumentException("주소는 100자 이하로 입력해주세요");
            }

            // 휴대폰 번호 : 필수, 숫자만, 11자리
            if (userPhoneNumber == null || userPhoneNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("휴대폰 번호는 필수입니다");
            }
            String digitsOnly = userPhoneNumber.replaceAll("[^0-9]", "");
            if (digitsOnly.length() != 11) {
                throw new IllegalArgumentException("휴대폰 번호는 숫자 11자리여야 합니다");
            }

            // 생년월일 : 필수, 8자리 yyyyMMdd
            if (userBirth == null || !userBirth.matches("^\\d{8}$")) {
                throw new IllegalArgumentException("생년월일은 yyyyMMdd 형식의 숫자 8자리여야 합니다");
            }

            // 성별 : 필수
            if (userGender == null || userGender.trim().isEmpty()) {
                throw new IllegalArgumentException("성별을 선택해주세요");
            }

            // 닉네임 : 필수, 길이 제한 10
            if (userNickname == null || userNickname.trim().isEmpty()) {
                throw new IllegalArgumentException("닉네임은 필수입니다");
            }
            if (userNickname.length() > 10) {
                throw new IllegalArgumentException("닉네임은 최대 10자까지 입력 가능합니다");
            }

            // 주민번호 : 필수, 13자리 숫자
            if (userCivilSerial == null || !userCivilSerial.matches("^\\d{13}$")) {
                throw new IllegalArgumentException("주민등록번호는 숫자 13자리여야 합니다");
            }
        }
    }

    @Data
    public static class CompJoinDTO {
        private String compUserName;
        private String compUserLoginId;
        private String compUserPassword;
        private String compUserPhone;
        private String compUserEmail;
        private String compUserNickname;
        private Long compRegNumber;
        private String compName;
        private String compCEOName;
        private String compAddress;

        public void validate() {
            // 이름: 필수, 숫자/특수문자 금지
            if (compUserName == null || compUserName.trim().isEmpty()) {
                throw new IllegalArgumentException("담당자명은 필수입니다.");
            }
            for (char c : compUserName.toCharArray()) {
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    throw new IllegalArgumentException("담당자명에는 숫자나 특수문자가 포함될 수 없습니다.");
                }
            }

            // 로그인 ID: 필수, 길이 제한
            if (compUserLoginId == null || compUserLoginId.length() < 4 || compUserLoginId.length() > 20) {
                throw new IllegalArgumentException("아이디는 4자 이상 20자 이하로 입력해주세요.");
            }

            //  비밀번호: 필수, 4자 이상
            if (compUserPassword == null || compUserPassword.length() < 4) {
                throw new IllegalArgumentException("비밀번호는 4자 이상 입력해주세요.");
            }

            //  전화번호: 필수, 숫자만, 10~11자리
            if (compUserPhone == null || compUserPhone.trim().isEmpty()) {
                throw new IllegalArgumentException("전화번호는 필수입니다.");
            }
            String onlyDigitsPhone = compUserPhone.replaceAll("[^0-9]", "");
            if (onlyDigitsPhone.length() < 10 || onlyDigitsPhone.length() > 11) {
                throw new IllegalArgumentException("전화번호는 숫자 10~11자리여야 합니다.");
            }

            //  이메일: 필수, @ 포함
            if (compUserEmail == null || !compUserEmail.contains("@")) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
            }

            //닉네임: 10자 이하
            if (compUserNickname != null && compUserNickname.length() > 10) {
                throw new IllegalArgumentException("닉네임은 10자 이하로 입력해주세요.");
            }

            // 사업자등록번호: 필수, 숫자 10자리
            if (compRegNumber == null || String.valueOf(compRegNumber).length() != 10) {
                throw new IllegalArgumentException("사업자등록번호는 10자리 숫자여야 합니다.");
            }

            // 회사명: 필수
            if (compName == null || compName.trim().isEmpty()) {
                throw new IllegalArgumentException("회사명은 필수입니다.");
            }

            // 대표자명: 필수
            if (compCEOName == null || compCEOName.trim().isEmpty()) {
                throw new IllegalArgumentException("대표자명은 필수입니다.");
            }

            // 주소: 필수
            if (compAddress == null || compAddress.trim().isEmpty()) {
                throw new IllegalArgumentException("주소는 필수입니다.");
            }
        }
    }

    @Data
    public static class LoginDTO {
        private String userLoginId;
        private String userPassWord;
        private String loginType;
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
