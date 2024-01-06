package com.blogger4.blogger4.service;

import com.blogger4.blogger4.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment (long postId, CommentDto commentDto);


    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    List<CommentDto> getAllcomments();
}
