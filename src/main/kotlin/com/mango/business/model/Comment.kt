package com.mango.business.model

import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import java.time.LocalDateTime

data class Comment(
    val id: CommentId,
    val text: String,
    val taskId: TaskId,
    val creatorId: UserId,
    val creationDate: LocalDateTime,
)
