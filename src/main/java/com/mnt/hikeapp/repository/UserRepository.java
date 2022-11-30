package com.mnt.hikeapp.repository;

import com.mnt.hikeapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByGoogleId(Long googleId);

}
