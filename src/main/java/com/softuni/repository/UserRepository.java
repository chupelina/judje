package com.softuni.repository;

import com.softuni.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("Select u.username from UserEntity u order by u.username")
    List<String> findAllUsernames();

    Optional<UserEntity> findByUsername(String username);
}
