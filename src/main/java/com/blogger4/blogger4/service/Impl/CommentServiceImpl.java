package com.blogger4.blogger4.service.Impl;

import com.blogger4.blogger4.entity.Comment;
import com.blogger4.blogger4.entity.Post;
import com.blogger4.blogger4.exception.ResourceNotFountException;
import com.blogger4.blogger4.payload.CommentDto;
import com.blogger4.blogger4.repositary.CommentRepositary;
import com.blogger4.blogger4.repositary.PostRepositary;
import com.blogger4.blogger4.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
   private PostRepositary postRepositary;
   private CommentRepositary commentRepositary;

    public CommentServiceImpl(PostRepositary postRepositary, CommentRepositary commentRepositary) {
        this.postRepositary = postRepositary;
        this.commentRepositary = commentRepositary;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post =postRepositary.findById(postId).orElseThrow(
                ()-> new ResourceNotFountException("post not found with id:"+postId)
        );

        Comment comment=new Comment();
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        comment.setPost(post);

      Comment savedComment=commentRepositary.save(comment);

       CommentDto dto= new CommentDto();
       dto.setId(savedComment.getId());
       dto.setName(savedComment.getName());
       dto.setEmail(savedComment.getEmail());
       dto.setBody(savedComment.getBody());

        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        commentRepositary.findById(commentId).orElseThrow(
                ()->new ResourceNotFountException("commnet not found with id:" + commentId)
        );
        commentRepositary.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
       List<Comment> comments = commentRepositary.findByPostId(postId);
       List<CommentDto>commentDtos=comments.stream().map(c->mapTODto(c)).collect(Collectors.toList());
       return commentDtos;
    }

    @Override
    public List<CommentDto> getAllcomments() {
        List<Comment> comments = commentRepositary.findAll();
        List<CommentDto> dtos = comments.stream().map(c->mapTODto(c)).collect(Collectors.toList());

        return dtos;

    }

    CommentDto mapTODto(Comment comment){
        CommentDto dto=new CommentDto();
       dto.setId(comment.getId());
       dto.setName(comment.getName());
       dto.setEmail(comment.getEmail());
       dto.setBody(comment.getBody());
       return dto;
    }
}
