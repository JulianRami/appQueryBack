
package com.appQueries.versionOne.Controller;

import com.appQueries.versionOne.controller.CommentController;
import com.appQueries.versionOne.dto.CommentDto;
import com.appQueries.versionOne.model.Comment;
import com.appQueries.versionOne.service.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    /**
     * Unit test for the getAllComments method of CommentController.
     */
    @Test
    void getAllComments() {
        // Test data setup
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        when(commentService.getAllComments()).thenReturn(Arrays.asList(comment1, comment2));

        // Call the controller method
        List<Comment> result = commentController.getAllComments();

        // Verify results
        assertEquals(2, result.size());
    }

    /**
     * Unit test for the getCommentById method of CommentController.
     */
    @Test
    void getCommentById() {
        // Test data setup
        Long commentId = 1L;
        Comment comment = new Comment();
        when(commentService.getCommentById(commentId)).thenReturn(comment);

        // Call the controller method
        ResponseEntity<Comment> result = commentController.getCommentById(commentId);

        // Verify results
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(comment, result.getBody());
    }

    /**
     * Unit test for the getCommentByIdQuery method of CommentController.
     */
    @Test
    void getCommentByIdQuery() {
        // Test data setup
        Long queryId = 2L;
        List<CommentDto> commentDtos = Arrays.asList(new CommentDto(), new CommentDto());
        when(commentService.getCommentByIdQuery(queryId)).thenReturn(commentDtos);

        // Call the controller method
        ResponseEntity<List<CommentDto>> result = commentController.getCommentByIdQuery(queryId);

        // Verify results
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(commentDtos, result.getBody());
    }

    /**
     * Unit test for the createComment method of CommentController.
     *
     * @throws ChangeSetPersister.NotFoundException if the entity is not found
     */
    @Test
    void createComment() throws ChangeSetPersister.NotFoundException {
        // Test data setup
        CommentDto commentDto = new CommentDto();
        Comment createdComment = new Comment();
        when(commentService.createComment(commentDto)).thenReturn(createdComment);

        // Call the controller method
        ResponseEntity<Comment> result = commentController.createComment(commentDto);

        // Verify results
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(createdComment, result.getBody());
    }

    /**
     * Unit test for the updateComment method of CommentController.
     */
    @Test
    void updateComment() {
        // Test data setup
        Long commentId = 1L;
        Comment updatedComment = new Comment();
        when(commentService.updateComment(commentId, updatedComment)).thenReturn(updatedComment);

        // Call the controller method
        ResponseEntity<Comment> result = commentController.updateComment(commentId, updatedComment);

        // Verify results
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedComment, result.getBody());
    }

    /**
     * Unit test for the deleteComment method of CommentController.
     */
    @Test
    void deleteComment() {
        // Test data setup
        Long commentId = 1L;

        // Call the controller method
        ResponseEntity<Void> result = commentController.deleteComment(commentId);

        // Verify results
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(commentService, times(1)).deleteComment(commentId);
    }
}
