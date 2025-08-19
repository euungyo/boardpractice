package com.example.boardpractice.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity{
    @CreationTimestamp
    @Column(updatable = false) //생성시 관여 X 옵션
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(insertable = false) //처음create시 관여 X옵션
    private LocalDateTime updatedAt;
}
