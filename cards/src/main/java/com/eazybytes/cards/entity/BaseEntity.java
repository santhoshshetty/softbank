package com.eazybytes.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;
    @Column(name="created_at")
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(name="updated_by")
    @LastModifiedBy
    private String updatedBy;
    @Column(name="updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
