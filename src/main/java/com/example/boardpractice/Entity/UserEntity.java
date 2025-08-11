package com.example.boardpractice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="user_table")

public class UserEntity {

    @Id
    @GeneratedValue
    private Long userId;

    @Column
    private String nickname;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String age;

    private String role;
}