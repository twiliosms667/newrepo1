package com.blogger4.blogger4.service.Impl;

import com.blogger4.blogger4.entity.Post;
import com.blogger4.blogger4.exception.ResourceNotFountException;
import com.blogger4.blogger4.payload.PostDto;
import com.blogger4.blogger4.repositary.PostRepositary;
import com.blogger4.blogger4.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepositary postRepo;

    public PostServiceImpl(PostRepositary postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public void createpost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        postRepo.save(post);
    }


    @Override
    public void deletePost(long id) {
    Post   post =postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFountException("Post not found with id: "+id));

        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPostss(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending());

        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> postss = pagePost.getContent();
        List<PostDto> dtos =  postss.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
      Post  post=postRepo.findById(postId).orElseThrow(
                ()->new ResourceNotFountException("post not Found with id:  "+postId)
        ) ;
      post.setTitle(postDto.getTitle());
      post.setContent(post.getContent());
      post.setDescription(post.getDescription());

        Post  savePost= postRepo.save(post);
       PostDto dto=mapToDto(savePost);
       return dto;
    }

    PostDto mapToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
         dto.setTitle(post.getTitle());
         dto.setDescription(post.getDescription());
         dto.setContent(post.getContent());
         return dto;
     }
}
