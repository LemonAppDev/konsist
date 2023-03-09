package com.mango.data.comment

import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CommentRepositoryImpl(
    private val commentJpaRepository: CommentJpaRepository,
    private val commentToCommentJpaEntityMapper: CommentToCommentJpaEntityMapper,
    private val commentJpaEntityToCommentMapper: CommentJpaEntityToCommentMapper,
) : CommentRepository {
    override val comments: List<Comment>
        get() = commentJpaRepository
            .findAll()
            .map { commentJpaEntityToCommentMapper(it) }

    override fun getComment(commentId: CommentId) = commentJpaRepository
        .findById(commentId.value)
        .getOrNull()
        ?.let { commentJpaEntityToCommentMapper(it) }

    override fun deleteComment(comment: Comment) = commentJpaRepository
        .delete(commentToCommentJpaEntityMapper(comment))

    override fun saveComment(comment: Comment) = commentJpaRepository
        .save(commentToCommentJpaEntityMapper(comment))
        .let { commentJpaEntityToCommentMapper(it) }
}
