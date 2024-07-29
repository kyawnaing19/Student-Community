package com.example.student_community.Repository;

import com.example.student_community.Model.Likes;
import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {

    public Likes findByUserAndPost(User user, Posts post);


}
