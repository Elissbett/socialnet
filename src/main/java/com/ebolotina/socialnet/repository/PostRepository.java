package com.ebolotina.socialnet.repository;

import com.ebolotina.socialnet.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface PostRepository extends JpaRepository<Post, Long> {
}
