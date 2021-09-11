package com.sujindar.Blog.Service;

import com.sujindar.Blog.Constants.PageSize;
import com.sujindar.Blog.Dao.CommentDao;
import com.sujindar.Blog.Entities.Blog;
import com.sujindar.Blog.Entities.Comment;
import com.sujindar.Blog.Entities.User;
import com.sujindar.Blog.Exception.CommentNotFoundException;
import com.sujindar.Blog.Service.Impl.CommentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CommentServiceTest {
    @Mock
    private CommentDao mockCommentDao;

    @InjectMocks
    private CommentServiceImpl commentService;

    public static Comment comment;
    public static Blog blog;
    public static List<Comment> comments;

    @BeforeAll
    public static void init() {
        comment = new Comment();
        comment.setContent("What a nice post!");
        comment.setId(1);
        User user = new User();
        user.setFirstName("Sujindar");
        user.setLastName("Selvaraj");
        user.setId(30);
        user.setBlogs(new HashSet<>());
        user.setComments(new ArrayList<>());
        blog = new Blog();
        blog.setContent("sdfsgasdfwrqwersf");
        blog.setId(20);
        blog.setTitle("First post!");
        blog.setUser(user);
        blog.setComments(new ArrayList<Comment>());
        user.getBlogs().add(blog);
        blog.getComments().add(comment);
        comment.setBlog(blog);
        comment.setUser(user);
        comments = new ArrayList<>();
        comments.add(comment);
    }

    @Test
    public void testCreateComment() {
        Mockito.when(mockCommentDao.save(comment)).thenReturn(comment);
        Comment receivedComment = commentService.createComment(comment);
        Assertions.assertEquals(receivedComment,comment);
    }

    @Test
    public void testGetCommentById() throws CommentNotFoundException {
        Mockito.when(mockCommentDao.findById(1)).thenReturn(Optional.of(comment));
        Comment receivedComment = commentService.getCommentById(1);
        Assertions.assertEquals(receivedComment,comment);
    }

    @Test
    public void testGetCommentByIdThrowsError() throws CommentNotFoundException {
        Mockito.when(mockCommentDao.findById(1)).thenReturn(Optional.empty());
        try {
            commentService.getCommentById(1);
        }
        catch (Exception e) {
            Assertions.assertEquals(e.getClass(),CommentNotFoundException.class);
        }
    }

    @Test
    public void testGetAllCommentByBlog() {
        Pageable pageable = PageRequest.of(0, PageSize.COMMENT_PAGE_SIZE, Sort.by("createdTime").ascending());
        Mockito.when(mockCommentDao.findAllByBlog(blog,pageable)).thenReturn(comments);
        List<Comment> receivedComments = commentService.getAllCommentByBlog(blog,0,PageSize.COMMENT_PAGE_SIZE,"createdTime");
        Assertions.assertEquals(receivedComments,comments);
    }

    @Test
    public void testDeleteCommentById() throws CommentNotFoundException{
        Mockito.doNothing().when(mockCommentDao).deleteById(1);
        Mockito.when(mockCommentDao.findById(1)).thenReturn(Optional.of(comment));
        Assertions.assertTrue(commentService.deleteCommentById(1));
    }

    @Test
    public void testDeleteCommentByIdThrowsError() throws CommentNotFoundException {
        Mockito.doNothing().when(mockCommentDao).deleteById(1);
        Mockito.when(mockCommentDao.findById(1)).thenReturn(Optional.empty());
        try {
            commentService.deleteCommentById(1);
        }
        catch (Exception e) {
            Assertions.assertEquals(CommentNotFoundException.class,e.getClass());
        }
    }

    @Test
    public void testUpdateCommentById() throws CommentNotFoundException {
        Mockito.when(mockCommentDao.findById(1)).thenReturn(Optional.of(comment));
        Mockito.when(mockCommentDao.save(comment)).thenReturn(comment);
        Comment newComment = new Comment();
        newComment.setContent("this is a new blog");
        newComment.setId(1);
        Comment receivedComment = commentService.updateCommentById(1,newComment);
        Assertions.assertEquals(receivedComment,newComment);
    }
}
