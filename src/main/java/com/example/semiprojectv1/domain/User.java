package com.example.semiprojectv1.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users4")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(unique=true, nullable=false)
    private String userid;

    @Column(nullable=false)
    private String passwd;

    @Column(nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String role;

    @CreationTimestamp
    private LocalDateTime regdate;
}
