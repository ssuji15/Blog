package com.sujindar.Blog.Service;

import com.sujindar.Blog.Constants.PageSize;
import com.sujindar.Blog.Dao.BlogDao;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.HashTags;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.BlogNotFoundException;
import com.sujindar.Blog.Exception.HashTagNotFoundException;
import com.sujindar.Blog.Exception.UserNotFoundException;
import com.sujindar.Blog.Service.Impl.BlogServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.*;

@SpringBootTest
public class BlogServiceTest {

    @Mock
    private BlogDao mockBlogDao;
    @Mock
    private HashTagsService hashTagsService;
    @Mock
    private UserService userService;

    @InjectMocks
    private BlogServiceImpl blogService;

    static Blog blog;
    static Set<Blog> blogs;
    static User user;
    static HashTags hashTag;
    static Comment comment;
    static List<Comment> comments;
    static List<Blog> blogList;
    @BeforeAll
    public static void initialize() {
        blog = new Blog();
        blog.setContent("This is my new Blog");
        blog.setId(1);
        blog.setTitle("New Blog");
        blog.setHashTags(new HashSet<>());
        blog.setComments(new ArrayList<>());

        user = new User();
        user.setFirstName("Sujindar");
        user.setId(2);
        user.setLastName("Selvaraj");
        user.setBlogs(new HashSet<>());
        user.setComments(new ArrayList<>());

        blog.setUser(user);
        user.getBlogs().add(blog);

        hashTag = new HashTags();
        hashTag.setId(20);
        hashTag.setName("Sports");
        hashTag.setBlogs(new HashSet<>());

        blog.getHashTags().add(hashTag);
        hashTag.getBlogs().add(blog);

        comment = new Comment();
        comment.setId(50);
        comment.setBlog(blog);
        comment.setContent("What a nice Blog");
        comment.setUser(user);

        blog.getComments().add(comment);
        user.getComments().add(comment);


        blogs = new HashSet<>();
        blogs.add(blog);

        comments = new ArrayList<>();
        comments.add(comment);

        blogList = new ArrayList<>(blogs);
    }

    @Test
    void testGetBlogById() throws BlogNotFoundException {
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.of(blog));
        Blog myBlog = blogService.getBlogById(1);
        Assertions.assertEquals(myBlog,blog);
    }
    @Test
    void testBlogByIdThrowsError() {
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.empty());
        try {
            blogService.getBlogById(1);
        }
        catch (Exception e) {
            Assertions.assertEquals(BlogNotFoundException.class,e.getClass());
        }
    }
    @Test
    void testGetAllBlogs() {
        Pageable pageable = PageRequest.of(0,PageSize.BLOG_PAGE_SIZE, Sort.by("id").ascending());
        Page<Blog> pages = new PageImpl<Blog>(blogList, pageable, blogs.size());
        Mockito.when(mockBlogDao.findAll(pageable)).thenReturn(pages);
        List<Blog> receivedBlogList = blogService.getAllBlog(0, PageSize.BLOG_PAGE_SIZE,"id");
        Assertions.assertEquals(blogList,receivedBlogList);
    }

    @Test
    void testGetAllBlogByHashTags() throws HashTagNotFoundException {
        Mockito.when(hashTagsService.getHashTagById(20)).thenReturn(hashTag);
        Mockito.when(mockBlogDao.findAllByHashTags(hashTag)).thenReturn(blogList);
        List<Blog> receivedBlogList = blogService.getAllBlogByHashTags(hashTag.getId());
        Assertions.assertEquals(blogList,receivedBlogList);
    }

    @Test
    public void testGetAllBlogByUser() throws UserNotFoundException {
        Mockito.when(userService.getUserById(2)).thenReturn(user);
        Mockito.when(mockBlogDao.findAllByUser(user)).thenReturn(blogList);
        List<Blog> receivedBlogList = blogService.getAllBlogByUser(user.getId());
        Assertions.assertEquals(blogList,receivedBlogList);
    }

    @Test
    public void testCreateBlog() {
        Mockito.when(mockBlogDao.save(blog)).thenReturn(blog);
        Blog savedBlog = blogService.createBlog(blog);
        Assertions.assertEquals(blog,savedBlog);
    }

    @Test
    public void testDeleteBlogById() throws BlogNotFoundException {
        Mockito.doNothing().when(mockBlogDao).deleteById(1);
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.of(blog));
        Assertions.assertTrue(blogService.deleteBlogById(1));
    }
    @Test
    public void testDeleteBlogByIdThrowsError() {
        Mockito.doNothing().when(mockBlogDao).deleteById(1);
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.empty());
        try{
            blogService.deleteBlogById(1);
        }
        catch(Exception e) {
            Assertions.assertEquals(BlogNotFoundException.class,e.getClass());
        }
    }
    @Test
    public void testUpdateBlogById() throws BlogNotFoundException {
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.of(blog));
        Mockito.when(mockBlogDao.save(blog)).thenReturn(blog);
        Blog updatedBlog = blogService.updateBlogById(1,blog);
        Assertions.assertEquals(updatedBlog,blog);
    }

    @Test
    public void testUpdateBlogByIdThrowError() {
        Mockito.when(mockBlogDao.findById(1)).thenReturn(Optional.empty());
        try {
            blogService.updateBlogById(1,blog);
        }
        catch (Exception e) {
            Assertions.assertEquals(BlogNotFoundException.class,e.getClass());
        }
    }
}
