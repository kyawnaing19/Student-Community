package com.example.student_community.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Date DOB;

    @Column
    private String profile;

    @Column
    private String address;

    @Column
    private String gender;

    @Column
    private String major;

    @Column(nullable = false)
    private String rno;

    @Column(nullable = false)
    private String phone;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;


}
