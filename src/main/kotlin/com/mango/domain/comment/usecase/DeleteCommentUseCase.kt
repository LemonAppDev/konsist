package com.mango.domain.comment.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivityFactory
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.CommentId
import com.mango.domain.common.LocalDateTimeFactory
import org.springframework.stereotype.Service

@Service
class DeleteCommentUseCase(
    private val commentRepository: CommentRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val commentActivityFactory: CommentActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(commentId: CommentId) {
        val comment = commentRepository.getComment(commentId)

        comment?.let {
            commentRepository.deleteComment(comment)

            val date = localDateTimeFactory()
            val activity = commentActivityFactory(comment, date, CommentActivityType.DELETE_COMMENT)
            activityRepository.addCommentActivity(activity)
        }
    }
}
