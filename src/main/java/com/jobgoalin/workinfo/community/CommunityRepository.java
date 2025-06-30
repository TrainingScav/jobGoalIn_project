package com.jobgoalin.workinfo.community;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    //스프링부트 jpa <<- 기본적인 CRUD


}
