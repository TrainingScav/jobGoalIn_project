package com.jobgoalin.workinfo.resume;

import com.jobgoalin.workinfo.user.LoginUser;
import com.jobgoalin.workinfo.user.User;
import jakarta.persistence.Column;
import lombok.Data;

public class ResumeRequest {

    // 이력서 등록
    @Data
    public static class ResumeRegisterDTO{
        private Long userId;
        private String userName;
        private String userNickname;
        private char gender;
        private String birthdate;
        private String email;
        private String phoneNumber;
        private String address;
        private String title;
        private String content;
        private Long positionId;
        private Long skillStackId;
        private char isExperienced;
        private char isShow;

        public Resume toEntity(User user) {
            return Resume.builder()
                    .user(user)
                    .title(title)
                    .name(userName)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .birthdate(birthdate)
                    .email(email)
                    .gender(gender)
                    .content(content)
                    .isExperienced(isExperienced)
                    .isShow(isShow)
                    .instId(user.getUserLoginId())
                    .build();
        }

        public static class UpdateDTO {

        }
    }
}
