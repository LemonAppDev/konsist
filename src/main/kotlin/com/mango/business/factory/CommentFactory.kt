package com.mango.business.factory

import com.mango.business.model.Comment
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CommentFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
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
