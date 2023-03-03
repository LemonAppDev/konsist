package com.mango.business.model.request.comment

import com.mango.business.model.value.TaskId

data class AddCommentRequestModel(
    val taskId: TaskId,
    val text: String,
)
