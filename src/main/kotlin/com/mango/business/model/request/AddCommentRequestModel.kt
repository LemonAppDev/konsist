package com.mango.business.model.request

import com.mango.business.model.value.TaskId

data class AddCommentRequestModel(
    val taskId: TaskId,
    val text: String,
)
