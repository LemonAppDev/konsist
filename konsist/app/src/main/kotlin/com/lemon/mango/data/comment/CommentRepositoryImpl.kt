package com.lemon.mango.data.comment

import com.lemon.mango.domain.comment.CommentRepository
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CommentRepositoryImpl(
    private val commentJpaEntityToCommentMapper: CommentJpaEntityToCommentMapper,
    private val commentJpaRepository: CommentJpaRepository,
    private val commentToCommentJpaEntityMapper: CommentToCommentJpaEntityMapper,
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
