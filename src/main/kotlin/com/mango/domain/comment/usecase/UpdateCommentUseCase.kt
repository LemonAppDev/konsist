package com.mango.domain.comment.usecase

import com.mango.domain.activity.usecase.UpdateCommentActivityUseCase
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import org.springframework.stereotype.Service

@Service
class UpdateCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
    private val updateCommentActivityUseCase: UpdateCommentActivityUseCase,
) {
    operator fun invoke(updateCommentRequestModel: UpdateCommentRequestModel) {
        val comment = getCommentOrThrowUseCase(updateCommentRequestModel.commentId)

        updateCommentRequestModel.text?.let {
            if (it != comment.text) {
                val newComment = comment.copy(text = it)

                commentRepository.saveComment(newComment)

                val date = localDateTimeFactory()
                updateCommentActivityUseCase(newComment, date, newComment.text, comment.text)
            }
        }
    }
}
