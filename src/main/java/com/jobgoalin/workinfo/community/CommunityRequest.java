package com.jobgoalin.workinfo.community;

import lombok.Data;

public class CommunityRequest {

@Data
    public static class SaveDTO  {
    private String title;
    private String content;
    private String instId;

    public Community toEntity() {

        return new Community(title, content, instId );
    }

    }
}
