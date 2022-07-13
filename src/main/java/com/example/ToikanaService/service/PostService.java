package com.example.ToikanaService.service;

import com.example.ToikanaService.dto.post.request.PostRequest;
import com.example.ToikanaService.dto.post.response.PostResponse;

import java.util.List;

public interface PostService extends BaseService<PostResponse, PostRequest>{
    List<PostResponse> getPostByUserId(Long id);
}
