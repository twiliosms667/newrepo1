package com.blogger4.blogger4.service;

import com.blogger4.blogger4.payload.PostDto;

import java.util.List;

public interface PostService {
    public void createpost(PostDto postDto);


    void deletePost(long id);

    List<PostDto> getAllPostss(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePost(long postId, PostDto postDto);
}
