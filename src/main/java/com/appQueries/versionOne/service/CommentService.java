package com.appQueries.versionOne.service;

import com.appQueries.versionOne.dto.CommentDto;
import com.appQueries.versionOne.model.Comment;
import com.appQueries.versionOne.model.Query;
import com.appQueries.versionOne.model.User;
import com.appQueries.versionOne.repository.CommentRepository;
import com.appQueries.versionOne.repository.QueryRepository;
import com.appQueries.versionOne.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * The `CommentService` class provides methods for managing comments related to queries in the application.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private final UserRepository userRepository;
    private final QueryRepository queryRepository;

    /**
     * Constructs a new instance of `CommentService`.
     *
     * @param userRepository  The repository for managing `User` entities.
     * @param queryRepository The repository for managing `Query` entities.
     */
    @Autowired
    public CommentService(UserRepository userRepository, QueryRepository queryRepository) {
        this.userRepository = userRepository;
        this.queryRepository = queryRepository;
    }

    /**
     * Retrieves all comments in the system.
     *
     * @return The list of all comments.
     */
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The retrieved comment.
     * @throws EntityNotFoundException If the comment with the specified ID is not found.
     */
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
    }

    /**
     * Creates a new comment.
     *
     * @param commentDto The data transfer object (DTO) containing the comment information.
     * @return The created comment.
     * @throws ChangeSetPersister.NotFoundException If the associated user or query is not found.
     */
    public Comment createComment(CommentDto commentDto) throws ChangeSetPersister.NotFoundException {
        Comment comment = new Comment();
        Optional<User> userOptional = userRepository.findByUsername(commentDto.getUser());
        if (userOptional.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        User user = userOptional.get();
        Optional<Query> queryOptional = queryRepository.findById(commentDto.getQueryId());
        if (queryOptional.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }
        Query query = queryOptional.get();
        comment.setQueryId(query);
        comment.setUserId(user);
        comment.setCommentText(commentDto.getCommentText());
        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment.
     *
     * @param id      The ID of the comment to update.
     * @param comment The updated comment data.
     * @return The updated comment.
     */
    public Comment updateComment(Long id, Comment comment) {
        getCommentById(id);
        comment.setId(id);
        return commentRepository.save(comment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     */
    public void deleteComment(Long id) {
        getCommentById(id);
        commentRepository.deleteById(id);
    }

    /**
     * Retrieves comments associated with a specific query.
     *
     * @param id The ID of the query to retrieve comments for.
     * @return The list of comments associated with the query.
     * @throws EntityNotFoundException If the query with the specified ID is not found.
     */
    public List<CommentDto> getCommentByIdQuery(Long id) {
        Query query = queryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Query not found with id: " + id));
        List<Comment> comments = commentRepository.findByQueryId(query);
        List<CommentDto> commentDtos = comments.stream()
                .map(this::convertToCommentDto)
                .collect(Collectors.toList());

        return commentDtos;
    }

    /**
     * Converts a Comment entity to a CommentDto data transfer object (DTO).
     *
     * @param comment The Comment entity to convert.
     * @return The corresponding CommentDto.
     */
    private CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setUser(comment.getUserId().getUsername());
        commentDto.setCommentText(comment.getCommentText());
        commentDto.setQueryId(comment.getQueryId().getId());
        return commentDto;
    }
}
