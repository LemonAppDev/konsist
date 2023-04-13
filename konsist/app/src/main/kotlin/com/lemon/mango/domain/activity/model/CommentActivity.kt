package com.lemon.mango.domain.activity.model

import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import java.time.LocalDateTime

data class CommentActivity(
    val id: CommentActivityId,
    val ownerId: UserId,
    val type: CommentActivityType,
    val commentId: CommentId,
    val taskId: TaskId,
    val date: LocalDateTime,
    val newValue: String?,
    val oldValue: String?,
)
