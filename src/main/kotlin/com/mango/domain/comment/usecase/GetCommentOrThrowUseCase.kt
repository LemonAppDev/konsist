package com.mango.domain.comment.usecase

import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service

@Service
class GetCommentOrThrowUseCase(
    private val commentRepository: CommentRepository,
) {
    operator fun invoke(commentId: CommentId): Comment {
        val comment = commentRepository.getComment(commentId)
        requireNotNull(comment) { "Comment with id: $commentId doesn't exist" }

        return comment
    }
}
