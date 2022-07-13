package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.post.request.PostRequest;
import com.example.ToikanaService.dto.post.response.PostResponse;
import com.example.ToikanaService.entity.PostEntity;
import com.example.ToikanaService.mapper.PostMapper;
import com.example.ToikanaService.repository.PostRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.service.PostService;
import com.example.ToikanaService.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostServiceImpl implements PostService {
    final PostRepository postRepository;

    final UserRepository userRepository;

    final UserService userService;

    @Override
    public PostResponse save(PostRequest t){
        PostEntity postEntity = postRepository
                .save(PostEntity.builder()
                        .userEntity(userRepository.getById(t.getUserId()))
                        .header(t.getHeader())
                        .build());

        return PostResponse.builder()
                .id(postEntity.getId())
                .header(postEntity.getHeader())
                .userId(postEntity.getUserEntity().getId())
                .build();
    }

    @Override
    public List<PostResponse> getAll() {
        return PostMapper.INSTANCE.toPostResponse(postRepository.findAll());
    }

    @Override
    public PostResponse findById(Long id) {
        return null;
    }

    @Override
    public List<PostResponse> getPostByUserId(Long id) {
        return null;
    }
}
