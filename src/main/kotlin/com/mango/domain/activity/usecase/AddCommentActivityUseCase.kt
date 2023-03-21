package com.mango.domain.activity.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class AddCommentActivityUseCase(
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        comment: Comment,
        type: CommentActivityType,
        date: LocalDateTime? = null,
        newValue: String? = null,
        oldValue: String? = null,
    ) {
        val activity = CommentActivity(
            uuidFactory.createCommentActivityId(),
            userRepository.getCurrentUser().id,
            type,
            comment.id,
            comment.taskId,
            date ?: localDateTimeFactory(),
            newValue,
            oldValue,
        )
        activityRepository.addCommentActivity(activity)
    }
}
