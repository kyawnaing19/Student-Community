package com.example.student_community.Model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "friends")
public class Friends
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int sender;

    @Column(nullable = false)
    private int receiver;

    @Column
    private String status;


    @Column
    private String ctime;

}
