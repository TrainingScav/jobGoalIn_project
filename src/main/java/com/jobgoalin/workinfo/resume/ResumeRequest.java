package com.jobgoalin.workinfo.resume;

import lombok.Data;

public class ResumeRequest {

    // 이력서 등록
    @Data
    public static class ResumeRegisterDTO{
        private String title;
        private String content;
    }
}
