package com.jobgoalin.workinfo.community;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityService {

    private final CommunityRepository communityRepository;

    // 전체 조회
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    // 단일 조회
    public Community findById(Long postId) {
        return communityRepository.findByPostId(postId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다. id=" + postId));
    }

    // 저장
    @Transactional
    public void save(Community community) {
        communityRepository.save(community);
    }

    // 수정
    @Transactional
    public void update(Long postId, Community updated) {
        Community origin = findById(postId);
        origin.setTitle(updated.getTitle());
        origin.setContent(updated.getContent());
        // 작성자(instId)와 작성일(instDate)는 변경하지 않습니다
    }

    // 삭제
    @Transactional
    public void delete(Long postId) {
        communityRepository.deleteById(postId);
    }
}