package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommunityService {
    private static final Logger log = LoggerFactory.getLogger(CommunityService.class);
    private final CommunityRepository communityRepository;


    // 커뮤니티 게시글 작성
    @Transactional
    public Community save(CommunityRequest.SaveDTO saveDTO, User sessionUser){
        Community community = saveDTO.toEntity(sessionUser);
        communityRepository.save(community);
        return community;
    }

    //커뮤니티 게시글 목록 조회
    public List<Community> findAll(){
        List<Community> communityList = communityRepository.findAllWithInstId();
        return communityList;
    }


}
