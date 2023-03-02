package com.mango.business.model.request

import com.mango.business.model.value.CommentId

data class UpdateCommentRequestModel(
    val commentId: CommentId,
    val text: String?,
)
