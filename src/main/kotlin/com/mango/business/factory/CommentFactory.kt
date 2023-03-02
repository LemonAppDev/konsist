package com.mango.business.factory

import com.mango.business.model.Comment
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Service

@Service
class CommentFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(
        comment: String,
        taskId: TaskId,
        creatorId: UserId,
    ) = Comment(
        uuidFactory.createCommentId(),
        comment,
        taskId,
        creatorId,
        localDateTimeFactory(),
    )
}
