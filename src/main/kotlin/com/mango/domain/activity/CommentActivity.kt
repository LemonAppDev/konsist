package com.mango.domain.activity

import com.mango.domain.comment.model.CommentId
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class CommentActivity(
    val ownerId: UserId,
    val type: CommentActivityType,
    val commentId: CommentId,
    val taskId: TaskId,
    val date: LocalDateTime,
    val newValue: String?,
    val oldValue: String?,
)
