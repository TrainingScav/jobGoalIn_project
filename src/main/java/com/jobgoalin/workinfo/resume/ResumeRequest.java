package com.jobgoalin.workinfo.resume;

import jakarta.persistence.Column;
import lombok.Data;

public class ResumeRequest {

    // 이력서 등록
    @Data
    public static class ResumeRegisterDTO{
        private Long userId;
        private String userNickname;
        private String title;
        private String content;
        private Long positionId;
        private Long skillStackId;
        private char isExperienced;
        private char isShow;
    }
}
