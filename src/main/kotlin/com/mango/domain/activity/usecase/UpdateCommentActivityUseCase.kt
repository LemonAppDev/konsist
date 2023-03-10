package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.CommentActivity
import com.mango.domain.activity.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateCommentActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        comment: Comment,
        date: LocalDateTime,
        newValue: String,
        oldValue: String,
    ) = CommentActivity(
        userRepository.getCurrentUser().id,
        CommentActivityType.UPDATE_COMMENT,
        comment.id,
        comment.taskId,
        date,
        newValue,
        oldValue,
    ).also {
        activityRepository.addCommentActivity(it)
    }
}
