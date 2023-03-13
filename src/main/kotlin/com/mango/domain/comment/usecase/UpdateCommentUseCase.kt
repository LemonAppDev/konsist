package com.mango.domain.comment.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivityFactory
import com.mango.domain.activity.CommentActivityType
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import org.springframework.stereotype.Service

@Service
class UpdateCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
    private val commentActivityFactory: CommentActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(updateCommentRequestModel: UpdateCommentRequestModel) {
        val comment = getCommentOrThrowUseCase(updateCommentRequestModel.commentId)

        updateCommentRequestModel.text?.let {
            if (it != comment.text) {
                val newComment = comment.copy(text = it)

                commentRepository.saveComment(newComment)

                val date = localDateTimeFactory()
                val activity = commentActivityFactory(newComment, date, CommentActivityType.UPDATE_COMMENT, newComment.text, comment.text)
                activityRepository.addCommentActivity(activity)
            }
        }
    }
}
