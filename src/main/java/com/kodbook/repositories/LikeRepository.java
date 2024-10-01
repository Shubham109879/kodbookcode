package com.kodbook.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodbook.entities.Like;
import com.kodbook.entities.Post;
import com.kodbook.entities.User;

public interface LikeRepository extends JpaRepository<Like, Long>
{
  Optional<Like> findByUserAndPost(User user,Post post);
}
