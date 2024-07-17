package com.example.student_community.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int sender;

    @Column
    private int receiver;

    @Column
    private String status;

    @CurrentTimestamp
    private LocalDateTime created_at;
}
