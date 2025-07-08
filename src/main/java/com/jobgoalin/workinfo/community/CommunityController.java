package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class CommunityController {
    private static final Logger log = LoggerFactory.getLogger(CommunityController.class);
    private final CommunityService communityService;

}
