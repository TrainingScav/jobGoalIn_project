package com.jobgoalin.workinfo.community;

import lombok.Data;

public class CommunityRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String instId;
        private String postPassword;

        public Community toEntity() {
            return Community.builder()
                    .title(title)
                    .content(content)
                    .instId(instId)
                    .postPassword(postPassword)
                    .build();
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
        private String instId;
        private String postPassword;

        public Community toEntity() {
            return Community.builder()
                    .title(title)
                    .content(content)
                    .instId(instId)
                    .postPassword(postPassword)
                    .build();
        }
    }
}
