package com.blogger4.blogger4.controller;

import com.blogger4.blogger4.entity.Comment;
import com.blogger4.blogger4.payload.CommentDto;
import com.blogger4.blogger4.repositary.PostRepositary;
import com.blogger4.blogger4.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    private CommentService commentService;

    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam("postId") long postId, @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment is deleted!!", HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId){
        List<CommentDto> commentDto = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
       List <CommentDto>commentDtos=commentService.getAllcomments();
       return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }
}