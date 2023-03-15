package com.mango.domain.comment.usecase

import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service

@Service
class DeleteCommentUseCase(
    private val commentRepository: CommentRepository,
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
) {
    operator fun invoke(commentId: CommentId) {
        val comment = commentRepository.getComment(commentId)

        comment?.let {
            commentRepository.deleteComment(comment)

            addCommentActivityUseCase(comment, CommentActivityType.DELETE_COMMENT)
        }
    }
}
