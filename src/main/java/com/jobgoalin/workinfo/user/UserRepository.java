package com.jobgoalin.workinfo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserLoginId(String userLoginId);
    boolean existsByUserLoginId(String userLoginId);
    boolean existsByUserEmail(String userEmail);
    boolean existsByUserCivilSerial(String userCivilSerial);

    @Query("SELECT u FROM User u where u.userLoginId = :id AND u.userPassWord = :password")
    Optional<User> findByUserIdAndPassword(@Param("id")String userLoginId, @Param("password") String userPassword);
}