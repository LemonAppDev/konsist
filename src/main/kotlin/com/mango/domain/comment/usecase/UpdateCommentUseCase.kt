package com.mango.domain.comment.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.request.UpdateCommentRequestModel
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.activity.UpdateCommentActivityFactory
import org.springframework.stereotype.Service

@Service
class UpdateCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val updateCommentActivityFactory: UpdateCommentActivityFactory,
    private val activityRepository: ActivityRepository,
    private val getCommentOrThrowUseCase: GetCommentOrThrowUseCase,
) {
    operator fun invoke(updateCommentRequestModel: UpdateCommentRequestModel) {
        val comment = getCommentOrThrowUseCase(updateCommentRequestModel.commentId)

        updateCommentRequestModel.text?.let {
            if (updateCommentRequestModel.text != comment.text) {
                val newComment = comment.copy(text = it)

                commentRepository.saveComment(newComment)

                val date = localDateTimeFactory()
                val activity = updateCommentActivityFactory(comment.taskId, date, comment.text, newComment.text)
                activityRepository.addActivity(activity)
            }
        }
    }
}
