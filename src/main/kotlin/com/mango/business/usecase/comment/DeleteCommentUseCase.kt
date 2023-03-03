package com.mango.business.usecase.comment

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.activity.task.DeleteCommentActivityFactory
import com.mango.business.model.value.CommentId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
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
