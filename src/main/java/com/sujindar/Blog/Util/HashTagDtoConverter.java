package com.sujindar.Blog.Util;

import com.sujindar.Blog.Dto.HashTagDto;
import com.sujindar.Blog.Entities.HashTags;

import java.util.ArrayList;
import java.util.List;


public abstract class HashTagDtoConverter {

    public static HashTags convertHashTagDtoToHashTags(HashTagDto hashTagDto) {
        HashTags hashTags = new HashTags();
        hashTags.setId(hashTagDto.getId());
        hashTags.setName(hashTagDto.getName());
        return hashTags;
    }

    public static HashTagDto convertHashTagsToHashTagsDto(HashTags hashTags) {
        HashTagDto hashTagDto = new HashTagDto();
        hashTagDto.setId(hashTags.getId());
        hashTagDto.setName(hashTags.getName());
        hashTagDto.setCreatedTime(hashTags.getCreatedTime());
        return hashTagDto;
    }
    public static List<HashTagDto> convertHashTagsToHashTagsDto(List<HashTags> hashTags) {
        List<HashTagDto> hashTagDtos = new ArrayList<>();
        hashTags.forEach(hashTag -> {
            hashTagDtos.add(HashTagDtoConverter.convertHashTagsToHashTagsDto(hashTag));
        });
        return hashTagDtos;
    }
}
