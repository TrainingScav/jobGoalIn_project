package com.jobgoalin.workinfo.company;


import com.jobgoalin.workinfo.user.User;
import lombok.Data;

public class CompanyRequest {

    @Data
    public static class SaveDTO {
        private Long companyId;
        private String companyName;
        private String companyDesc;
        private String companyCeoName;
        private String homepageUrl;
        private String phoneNumber;
        private String companyEmail;
        private String companyAddress;

        // 필수값 유효성 체크
        /*
        public void validate() {
            if (companyName == null || companyName.trim().isEmpty()) {
                throw new Exception("");
            }
            if (companyDesc == null || companyDesc.trim().isEmpty()) {
                throw new Exception("");
            }
            if (companyCeoName == null || companyCeoName.trim().isEmpty()) {
                throw new Exception("");
            }
        }
         */

        public CompanyInfo toEntity() {

            return CompanyInfo.builder()
                    .companyId(companyId)
                    .companyName(companyName)
                    .companyDesc(companyDesc)
                    .companyCeoName(companyCeoName)
                    .homepageUrl(homepageUrl)
                    .phoneNumber(phoneNumber)
                    .companyEmail(companyEmail)
                    .companyAddress(companyAddress)
                    .build();
        }
    }

    @Data
    public static class SaveReviewDTO {

        private Long reviewId;
        private String content;
        private boolean isCurrentEmployee;
        private boolean isRecommended;
        
        // selectBox 값 String -> boolean 변환을 위한 변수
        private String isCurrentEmployeeYn;
        private String isRecommendedYn;

        public CompanyReview toEntity(User user, CompanyInfo companyInfo) {

            return CompanyReview.builder()
                    .content(content)
                    .user(user)
                    .companyInfo(companyInfo)
                    .isCurrentEmployee(isCurrentEmployee)
                    .isRecommended(isRecommended)
                    .build();
        }
    }
}
