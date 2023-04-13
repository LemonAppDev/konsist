package com.lemon.mango.application.model.comment

import com.lemon.mango.domain.task.model.TaskId

data class AddCommentRequestModel(
    val taskId: TaskId,
    val text: String,
)
