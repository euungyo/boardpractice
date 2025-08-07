package com.example.boardpractice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user_table")
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nickname;
    private String name;
    private int age;
    private LocalDate createdAt;
}
