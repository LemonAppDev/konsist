package com.mango.domain.comment.usecase

import com.mango.domain.activity.usecase.DeleteCommentActivityUseCase
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.CommentId
import com.mango.domain.common.LocalDateTimeFactory
import org.springframework.stereotype.Service

@Service
class DeleteCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val deleteCommentActivityUseCase: DeleteCommentActivityUseCase,
) {
    operator fun invoke(commentId: CommentId) {
        val comment = commentRepository.getComment(commentId)

        comment?.let {
            commentRepository.deleteComment(comment)

            val date = localDateTimeFactory()
            deleteCommentActivityUseCase(comment, date)
        }
    }
}
