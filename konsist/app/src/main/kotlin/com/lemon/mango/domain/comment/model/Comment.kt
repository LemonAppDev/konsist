package com.lemon.mango.domain.comment.model

import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class Comment(
    val id: CommentId,
    val text: String,
    val taskId: TaskId,
    val creatorId: UserId,
    val creationDate: LocalDateTime,
)
