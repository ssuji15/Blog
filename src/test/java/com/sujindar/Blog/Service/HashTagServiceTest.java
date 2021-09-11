package com.sujindar.Blog.Service;

import com.sujindar.Blog.Dao.HashTagsDao;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Service.Impl.HashTagsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HashTagServiceTest {

    @Mock
    private HashTagsDao mockHashTagDao;

    @InjectMocks
    private HashTagsServiceImpl hashTagsService;

    private static HashTags hashTag;
    private static List<HashTags> hashTags;

    @BeforeAll
    public static void init() {
        hashTag = new HashTags();
        hashTag.setId(1);
        hashTag.setName("sport");
        hashTags = new ArrayList<>();
        hashTags.add(hashTag);
    }

    @Test
    public void testGetAllHashTag() {
        Mockito.when(mockHashTagDao.findAll()).thenReturn(hashTags);
        Assertions.assertEquals(hashTags,hashTagsService.getAllHashTag());
    }
    @Test
    public void testDeleteHashTagById() throws HashTagNotFoundException{
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.of(hashTag));
        Mockito.doNothing().when(mockHashTagDao).deleteById(1);
        Assertions.assertTrue(hashTagsService.deleteHashTagById(1));
    }
    @Test
    public void testDeleteHashTagByIdThrowError() throws HashTagNotFoundException{
        Mockito.doNothing().when(mockHashTagDao).deleteById(1);
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.empty());
        try {
            hashTagsService.deleteHashTagById(1);
        }
        catch(Exception e) {
            Assertions.assertEquals(HashTagNotFoundException.class,e.getClass());
        }
    }
    @Test
    public void testCreateHashTag() {
        Mockito.when(mockHashTagDao.save(hashTag)).thenReturn(hashTag);
        Assertions.assertEquals(hashTag,hashTagsService.createHashTag(hashTag));
    }
    @Test
    public void testGetHashTagById() throws HashTagNotFoundException {
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.of(hashTag));
        Assertions.assertEquals(hashTag,hashTagsService.getHashTagById(1));
    }
    @Test
    public void testGetHashTagByIdThrowError() {
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.empty());
        try {
            hashTagsService.getHashTagById(1);
        }
        catch (Exception e) {
            Assertions.assertEquals(HashTagNotFoundException.class,e.getClass());
        }
    }
    @Test
    public void testUpdateHashTagById() throws HashTagNotFoundException{
        Mockito.when(mockHashTagDao.save(hashTag)).thenReturn(hashTag);
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.of(hashTag));
        HashTags updatedHashTag = new HashTags();
        updatedHashTag.setId(1);
        updatedHashTag.setName("sport");
        Assertions.assertEquals(updatedHashTag,hashTagsService.updateHashTagById(1,updatedHashTag));
    }

    @Test
    public void testUpdateHashTagByIdThrowsError() {
        Mockito.when(mockHashTagDao.save(hashTag)).thenReturn(hashTag);
        Mockito.when(mockHashTagDao.findById(1)).thenReturn(Optional.empty());
        HashTags updatedHashTag = new HashTags();
        updatedHashTag.setId(1);
        updatedHashTag.setName("sport");
        try {
            hashTagsService.updateHashTagById(1,updatedHashTag);
        }
        catch(Exception e) {
            Assertions.assertEquals(HashTagNotFoundException.class,e.getClass());
        }
    }


}
