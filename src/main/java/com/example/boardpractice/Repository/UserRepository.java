package com.example.boardpractice.Repository;

import com.example.boardpractice.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Boolean existsByNickname(String nickname);

    UserEntity findByNickname(String nickname);
}
