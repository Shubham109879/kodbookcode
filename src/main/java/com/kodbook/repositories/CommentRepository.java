package com.kodbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.Comments;
import com.kodbook.entities.Post;

public interface CommentRepository extends JpaRepository<Comments, Long>
{
  List<Comments> findByPost(Post post);
}
