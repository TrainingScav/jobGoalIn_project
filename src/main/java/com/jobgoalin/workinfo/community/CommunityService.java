package com.jobgoalin.workinfo.community;


import com.jobgoalin.workinfo.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 커뮤니티 관련 비즈니스 로직 처리 계층
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private static final Logger log = LoggerFactory.getLogger(CommunityService.class);
    private final CommunityRepository communityRepository;
    /**
     * 게시글 저장하기
     */
    @Transactional
    public void save(CommunityRequest.SaveDTO saveDTO, User sessionUser){
        // 1. 게시글 조회

        // 2.


    }

}
