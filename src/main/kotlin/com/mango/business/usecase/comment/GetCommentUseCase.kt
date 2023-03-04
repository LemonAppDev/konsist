package com.mango.business.usecase.comment

import com.mango.business.model.Comment
import com.mango.business.model.value.CommentId
import com.mango.persistence.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class GetCommentUseCase(
    private val commentRepository: CommentRepository,
) {
    operator fun invoke(commentId: CommentId): Comment {
        val comment = commentRepository.getComment(commentId)
        requireNotNull(comment) { "Comment with id: $commentId doesn't exist" }

        return comment
    }
}
