package com.example.student_community.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
@JsonIgnore
    @JoinColumn(name = "post_id", nullable = false)

    private Posts post;


    @Column(name = "image_url", nullable = false)
    private String image_url;
}
