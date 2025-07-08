package com.jobgoalin.workinfo.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    //스프링부트 jpa <<- 기본적인 CRUD
    //전체 게시글 목록 조회 -> 모든 게시물 로드 후 게시물에 포함된 값 postId, title, instId

    //전체 게시글 목록 조회 -> 필요한 값 : 게시글 id, 게시글 title, 게시글 작성자 닉네임 (세션유저)
    //필요 테이블 Community, User
    @Query(value = "select c from community_post_info c join user_info u on c.inst_id = u.user_login_id",nativeQuery = true)
    List<Community> findAllJoinUser();


}
