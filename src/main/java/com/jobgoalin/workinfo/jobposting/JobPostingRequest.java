package com.jobgoalin.workinfo.jobposting;

import com.jobgoalin.workinfo._core.errors.exception.Exception400;
import com.jobgoalin.workinfo.company.CompanyInfo;
import com.jobgoalin.workinfo.user.CompUser;
import lombok.Data;

import java.time.LocalDate;

public class JobPostingRequest {

    @Data
    public static class SaveDTO {
        private CompanyInfo companyInfo;
        private String title;
        private String content;
        private String employmentType;
        private String location;
        private String requireCareerYears;
        private LocalDate deadline;

        // 필수값 유효성 체크
        /**/
        public void validate() {
            if (title == null || title.trim().isEmpty()) {
                throw new Exception400("제목을 입력 해 주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new Exception400("공고 내용을 입력 해 주세요.");
            }
            if (employmentType == null || employmentType.trim().isEmpty()) {
                throw new Exception400("고용 형태를 입력 해 주세요.");
            }
            if (location == null || location.trim().isEmpty()) {
                throw new Exception400("근무지를 입력 해 주세요.");
            }
            if (requireCareerYears == null || requireCareerYears.trim().isEmpty()) {
                throw new Exception400("요구 경력을 입력 해 주세요.");
            }
            if (deadline == null) {
                throw new Exception400("마감일을 선택 해 주세요.");
            }
        }


        public JobPostingBoard toEntity(CompanyInfo companyInfo) {

            return JobPostingBoard.builder()
                    .companyId(companyInfo.getCompanyId())
                    .title(title)
                    .companyName(companyInfo.getCompanyName())
                    .content(content)
                    .requireCareerYears(requireCareerYears)
                    .employmentType(employmentType)
                    .location(location)
                    .instId(companyInfo.getInstId())
                    .postedAt(LocalDate.now())
                    .deadline(deadline)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private CompanyInfo companyInfo;
        private String title;
        private String content;
        private String employmentType;
        private String location;
        private String requireCareerYears;
        private LocalDate deadline;

        // 필수값 유효성 체크
        /**/
        public void validate() {
            if (title == null || title.trim().isEmpty()) {
                throw new Exception400("제목을 입력 해 주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new Exception400("공고 내용을 입력 해 주세요.");
            }
            if (employmentType == null || employmentType.trim().isEmpty()) {
                throw new Exception400("고용 형태를 입력 해 주세요.");
            }
            if (location == null || location.trim().isEmpty()) {
                throw new Exception400("근무지를 입력 해 주세요.");
            }
            if (requireCareerYears == null || requireCareerYears.trim().isEmpty()) {
                throw new Exception400("요구 경력을 입력 해 주세요.");
            }
            if (deadline == null) {
                throw new Exception400("마감일을 선택 해 주세요.");
            }
        }


        public JobPostingBoard toEntity(CompanyInfo companyInfo) {

            return JobPostingBoard.builder()
                    .companyId(companyInfo.getCompanyId())
                    .title(title)
                    .companyName(companyInfo.getCompanyName())
                    .content(content)
                    .requireCareerYears(requireCareerYears)
                    .employmentType(employmentType)
                    .location(location)
                    .instId(companyInfo.getInstId())
                    .postedAt(LocalDate.now())
                    .deadline(deadline)
                    .build();
        }
    }
}
