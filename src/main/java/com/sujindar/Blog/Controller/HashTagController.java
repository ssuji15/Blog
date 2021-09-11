package com.sujindar.Blog.Controller;

import com.sujindar.Blog.Dto.HashTagDto;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Exception.InvalidHashTagDataException;
import com.sujindar.Blog.Service.Impl.HashTagsServiceImpl;
import com.sujindar.Blog.Util.HashTagDtoConverter;
import com.sujindar.Blog.Validators.HashTagControllerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hashTag")
public class HashTagController {

    @Autowired
    private HashTagsServiceImpl hashTagsService;


    @GetMapping("/")
    public ResponseEntity listHashTags() {
        List<HashTags> hashTags = hashTagsService.getAllHashTag();
        List<HashTagDto> hashTagDtos = HashTagDtoConverter.convertHashTagsToHashTagsDto(hashTags);
        return new ResponseEntity(hashTagDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getHashTagById(@PathVariable int id) throws HashTagNotFoundException {
        HashTags hashTag = hashTagsService.getHashTagById(id);
        HashTagDto responseHashTag = HashTagDtoConverter.convertHashTagsToHashTagsDto(hashTag);
        return new ResponseEntity(responseHashTag,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createHashTag(@RequestBody HashTagDto hashTagDto) throws InvalidHashTagDataException {
        HashTagControllerValidator.validateCreateHashTagDto(hashTagDto);
        HashTags hashTag = HashTagDtoConverter.convertHashTagDtoToHashTags(hashTagDto);
        HashTags createdHashTag = hashTagsService.createHashTag(hashTag);
        HashTagDto responseHashTag = HashTagDtoConverter.convertHashTagsToHashTagsDto(createdHashTag);
        return new ResponseEntity(responseHashTag,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHashTagById(@PathVariable int id) throws HashTagNotFoundException {
        hashTagsService.deleteHashTagById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateHashTagById(@RequestBody HashTagDto hashTagDto) throws HashTagNotFoundException, InvalidHashTagDataException {
        HashTagControllerValidator.validateUpdateHashTagDto(hashTagDto);
        HashTags hashTag = HashTagDtoConverter.convertHashTagDtoToHashTags(hashTagDto);
        HashTags updatedHashTag = hashTagsService.updateHashTagById(hashTag.getId(),hashTag);
        HashTagDto responseHashTag = HashTagDtoConverter.convertHashTagsToHashTagsDto(updatedHashTag);
        return new ResponseEntity(responseHashTag,HttpStatus.OK);
    }
}
