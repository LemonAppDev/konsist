package com.mango.domain.comment.usecase

import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.CommentId
import org.springframework.stereotype.Service

@Service
class UpdateCommentUseCase(
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
    private val commentRepository: CommentRepository,
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
) {
    operator fun invoke(commentId: CommentId, text: String?) {
        val comment = getCommentOrThrowUseCase(commentId)

        text?.let {
            if (it != comment.text) {
                val newComment = comment.copy(text = it)

                commentRepository.saveComment(newComment)
                addCommentActivityUseCase(
                    newComment,
                    CommentActivityType.UPDATE_COMMENT,
                    newValue = newComment.text,
                    oldValue = comment.text,
                )
            }
        }
    }
}
