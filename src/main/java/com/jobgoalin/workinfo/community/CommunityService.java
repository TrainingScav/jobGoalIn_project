package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 전체 게시글 조회
    public Page<Community> findAllPosts(Pageable pageable) {
        log.info("전체 게시글 조회 서비스 시작");
        Page<Community> communityPage = communityRepository.findAll(pageable);
        log.info("게시글 : {}",communityPage.getTotalElements());
        return communityPage;
    }

    // 단일 게시글 조회
    public Community findById(Long postId) {
        return communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id=" + postId));
    }

    // 게시글 생성
    @Transactional
    public Community savePost(CommunityRequest.SaveDTO dto) {
        return communityRepository.save(dto.toEntity());
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, CommunityRequest.UpdateDTO dto) {
        Community post = findById(postId);
        post.update(dto);
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        communityRepository.deleteById(postId);
    }

}
