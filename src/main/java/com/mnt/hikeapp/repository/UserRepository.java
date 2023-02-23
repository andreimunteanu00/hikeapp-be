package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByGoogleId(String googleId);

    @Query(nativeQuery = true, value = "SELECT EXISTS(SELECT 1 " +
            "              FROM user " +
            "              WHERE :value in (SELECT CASE :columnName " +
            "                                WHEN 'email' THEN u.email " +
            "                                WHEN 'username' THEN u.username " +
            "                                ELSE null END " +
            "                     FROM User u))")
    Integer checkFieldDuplicate(@Param("columnName") String columnName, @Param("value") String value);
}
