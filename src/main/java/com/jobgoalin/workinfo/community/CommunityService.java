package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 전체 게시글 조회
    public List<Community> findAllPosts() {
        return communityRepository.findAll();
    }

    // 단일 게시글 조회
    public Community findById(Long postId) {
        return communityRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다. id=" + postId));
    }

    // 게시글 생성
    @Transactional
    public Community savePost(CommunityRequest.SaveDTO dto) {
        Community post = Community.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .instId(dto.getInstId())
                .postPassword(dto.getPostPassword())
                .build();
        return communityRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public void updatePost(Long postId, CommunityRequest.UpdateDTO dto) {
        Community post = findById(postId);
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        // instId, instDate는 변경하지 않음
    }

    // 게시글 삭제
    @Transactional
    public void deletePost(Long postId) {
        communityRepository.deleteById(postId);
    }

}
