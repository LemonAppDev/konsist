package com.mango.application.model.comment

import com.mango.domain.task.model.TaskId

data class AddCommentRequestModel(
    val taskId: TaskId,
    val text: String,
)
