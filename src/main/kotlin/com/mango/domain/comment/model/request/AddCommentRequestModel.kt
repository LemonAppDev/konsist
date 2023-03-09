package com.mango.domain.comment.model.request

import com.mango.domain.task.model.TaskId

data class AddCommentRequestModel(
    val taskId: TaskId,
    val text: String,
)
