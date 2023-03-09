package com.mango.domain.comment.usecase

import com.mango.data.activity.ActivityRepository
import com.mango.data.comment.CommentRepository
import com.mango.domain.comment.model.CommentId
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.activity.DeleteCommentActivityFactory
import org.springframework.stereotype.Service

@Service
class DeleteCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val deleteCommentActivityFactory: DeleteCommentActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(commentId: CommentId) {
        val comment = commentRepository.getComment(commentId)

        comment?.let {
            commentRepository.deleteComment(comment)

            val date = localDateTimeFactory()
            val activity = deleteCommentActivityFactory(comment.taskId, date, comment.id)
            activityRepository.addActivity(activity)
        }
    }
}
