package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.domain.activity.model.CommentActivityType
import com.lemon.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.lemon.mango.domain.comment.CommentRepository
import com.lemon.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service

@Service
class DeleteCommentUseCase(
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
    private val commentRepository: CommentRepository,
) {
    operator fun invoke(commentId: CommentId) {
        val comment = commentRepository.getComment(commentId)

        comment?.let {
            commentRepository.deleteComment(comment)

            addCommentActivityUseCase(comment, CommentActivityType.DELETE_COMMENT)
        }
    }
}
