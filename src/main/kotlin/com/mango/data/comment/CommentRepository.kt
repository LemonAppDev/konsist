package com.mango.data.comment

import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CommentRepository(
    private val commentJpaRepository: CommentJpaRepository,
    private val commentToCommentJpaEntityMapper: CommentToCommentJpaEntityMapper,
    private val commentJpaEntityToCommentMapper: CommentJpaEntityToCommentMapper,
) {
    val comments: List<Comment>
        get() = commentJpaRepository
            .findAll()
            .map { commentJpaEntityToCommentMapper(it) }

    fun getComment(commentId: CommentId) = commentJpaRepository
        .findById(commentId.value)
        .getOrNull()
        ?.let { commentJpaEntityToCommentMapper(it) }

    fun deleteComment(comment: Comment) = commentJpaRepository
        .delete(commentToCommentJpaEntityMapper(comment))

    fun saveComment(comment: Comment) = commentJpaRepository
        .save(commentToCommentJpaEntityMapper(comment))
        .let { commentJpaEntityToCommentMapper(it) }
}
