package com.lemon.mango.domain.comment

import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UUIDFactory
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class CommentFactory(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        taskId: TaskId,
        text: String,
    ) = Comment(
        uuidFactory.createCommentId(),
        text,
        taskId,
        userRepository.getCurrentUser().id,
        localDateTimeFactory(),
    )
}
