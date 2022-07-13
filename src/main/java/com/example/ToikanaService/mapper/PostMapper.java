package com.example.ToikanaService.mapper;

import com.example.ToikanaService.dto.post.response.PostResponse;
import com.example.ToikanaService.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    List<PostResponse> toPostResponse(List<PostEntity> postEntities);
}
