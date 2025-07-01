package com.jobgoalin.workinfo.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    //스프링부트 jpa <<- 기본적인 CRUD


    //전체 게시글 목록 조회 -> 필요한 값 : 게시글 id, 게시글 title, 게시글 작성자 id (세션유저)
    @Query("SELECT c FROM Community c")
    List<CommunityRequest.SaveDTO> findAllWithInstId();

}
