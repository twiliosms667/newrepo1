package com.blogger4.blogger4.controller;

import com.blogger4.blogger4.payload.PostDto;
import com.blogger4.blogger4.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/postss")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> CreatePost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        postService.createpost(postDto);
        return new ResponseEntity<>("post is created", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String>deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted!!",HttpStatus.OK);
    }
    //http://localhost:8080/api/postss?pageNo=0&pageSize=5&sortDir=ase
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity <List<PostDto>> getAllPostss(
        @RequestParam(name="pageNo", defaultValue = "0",required= false) int pageNo,
        @RequestParam(name="pageSize",defaultValue = "3",required = false)int pageSize,
        @RequestParam(name="sortBy",defaultValue="id",required=false) String sortBy,
        @RequestParam(name="sortDir",defaultValue = "asc",required=false)String sortDir

){


    List<PostDto> postDtos= postService.getAllPostss(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);
    }

    //http://localhost:8080/api/postss/{postID}
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<PostDto> updatePost
    (  @RequestParam("postId") long postId,
        @RequestBody PostDto postDto){

       PostDto dto= postService.updatePost(postId,postDto);
       return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
