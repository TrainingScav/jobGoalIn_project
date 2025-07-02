package com.jobgoalin.workinfo.company;


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
}
