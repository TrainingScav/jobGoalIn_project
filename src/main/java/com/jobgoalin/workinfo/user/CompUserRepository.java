package com.jobgoalin.workinfo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CompUserRepository extends JpaRepository<CompUser, Long> {
    boolean existsByCompUserLoginId(String userLoginId);
    boolean existsByCompUserEmail(String userEmail);

    @Query("SELECT cu FROM CompUser cu where cu.compUserLoginId = :id AND cu.compUserPassword = :password")
    Optional<CompUser> findByUserIdAndPassword(@Param("id")String userLoginId, @Param("password") String userPassword);
}
