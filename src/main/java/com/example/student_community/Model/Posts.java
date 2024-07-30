package com.example.student_community.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CurrentTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getUserName(){
        return user.getName();
    }

    public String getEmail(){
        return user.getEmail();
    }

    public String getProfile(){
        return user.getProfile();
    }

    @Column(name = "content")
    private String content;

    @Column(name = "audience")
    private String audience;

    @CurrentTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Images> images;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Likes> like;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Comments> comments;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private Posts parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Posts> posts;

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", audience='" + audience + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
