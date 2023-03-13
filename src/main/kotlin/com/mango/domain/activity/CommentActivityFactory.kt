package com.mango.domain.activity

import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.Comment
import com.mango.domain.common.UUIDFactory
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentActivityFactory(
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        comment: Comment,
        date: LocalDateTime,
        type: CommentActivityType,
        newValue: String? = null,
        oldValue: String? = null,
    ) = CommentActivity(
        uuidFactory.createCommentActivityId(),
        userRepository.getCurrentUser().id,
        type,
        comment.id,
        comment.taskId,
        date,
        newValue,
        oldValue,
    )
}
