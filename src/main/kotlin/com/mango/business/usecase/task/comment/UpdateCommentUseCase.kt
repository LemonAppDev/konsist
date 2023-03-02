package com.mango.business.usecase.task.comment

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.activity.task.UpdateCommentActivityFactory
import com.mango.business.model.request.UpdateCommentRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class UpdateCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val updateCommentActivityFactory: UpdateCommentActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(updateCommentRequestModel: UpdateCommentRequestModel) {
        val comment = commentRepository.getComment(updateCommentRequestModel.commentId)
        requireNotNull(comment) { "Comment doesn't exist: commentId: ${updateCommentRequestModel.commentId}" }

        updateCommentRequestModel.text?.let {
            if (updateCommentRequestModel.text != comment.text) {
                val newComment = comment.copy(text = it)

                commentRepository.updateComment(newComment)

                val date = localDateTimeFactory()
                val activity = updateCommentActivityFactory(comment.taskId, date, comment.text, newComment.text)
                activityRepository.addActivity(activity)
            }
        }
    }
}
