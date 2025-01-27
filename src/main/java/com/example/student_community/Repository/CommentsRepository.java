package com.example.student_community.Repository;

import com.example.student_community.Model.Comments;
import com.example.student_community.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {

    @Query("SELECT c FROM Comments c WHERE c.post = :post AND c.parentComment IS NULL")
    List<Comments> findComment(@Param("post") Posts post);
}
