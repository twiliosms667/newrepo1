package com.blogger4.blogger4.repositary;

import com.blogger4.blogger4.entity.Comment;
import com.blogger4.blogger4.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentRepositary extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);

}
