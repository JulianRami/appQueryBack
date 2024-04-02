package com.appQueries.versionOne.controller;

import com.appQueries.versionOne.dto.CommentDto;
import com.appQueries.versionOne.model.Comment;
import com.appQueries.versionOne.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments.
     *
     * @return List of Comment objects.
     */
    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id Comment ID.
     * @return ResponseEntity containing the Comment object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * Retrieves comments by the associated query ID.
     *
     * @param idQuery Query ID.
     * @return ResponseEntity containing a List of CommentDto objects.
     */
    @GetMapping("/byIdQuery/{idQuery}")
    public ResponseEntity<List<CommentDto>> getCommentByIdQuery(@PathVariable Long idQuery) {
        List<CommentDto> commentDto = commentService.getCommentByIdQuery(idQuery);
        return ResponseEntity.ok(commentDto);
    }

    /**
     * Creates a new comment.
     *
     * @param comment CommentDto object containing comment details.
     * @return ResponseEntity containing the created Comment object.
     * @throws ChangeSetPersister.NotFoundException if associated query ID is not found.
     */
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDto comment) throws ChangeSetPersister.NotFoundException {
        Comment createdComment = commentService.createComment(comment);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdComment.getId()).toUri();
        return ResponseEntity.created(location).body(createdComment);
    }

    /**
     * Updates an existing comment by its ID.
     *
     * @param id      Comment ID.
     * @param comment Comment object containing updated details.
     * @return ResponseEntity containing the updated Comment object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id Comment ID.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
