package com.mango.domain.activity

import com.mango.domain.comment.model.Comment
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentActivityFactory(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        comment: Comment,
        date: LocalDateTime,
        type: CommentActivityType,
        newValue: String? = null,
        oldValue: String? = null,
    ) = CommentActivity(
        userRepository.getCurrentUser().id,
        type,
        comment.id,
        comment.taskId,
        date,
        newValue,
        oldValue,
    )
}
