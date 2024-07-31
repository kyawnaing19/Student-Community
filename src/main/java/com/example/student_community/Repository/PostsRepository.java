package com.example.student_community.Repository;

import com.example.student_community.Model.Posts;
import com.example.student_community.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

    @Query("SELECT p FROM Posts p WHERE p.user = :user AND p.parentId is NULL AND p.audience IN :audiences order by p.id desc ")
    List<Posts> findByUserAndAudienceIn(@Param("user") User user, @Param("audiences") List<String> audiences);

    @Query("SELECT p FROM Posts p LEFT JOIN FETCH p.parentId WHERE p.user.id = :userId order by  p.id desc")
    List<Posts> findAllPostsByUserId(@Param("userId") int userId);

    @Query("SELECT p FROM Posts p WHERE p.user = :user and p.parentId is null AND p.audience IN :audiences order by p.id desc ")
    List<Posts> findOtherWall(@Param("user") User user, @Param("audiences") List<String> audiences);
}
