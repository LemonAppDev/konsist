package com.mango.domain.comment.model.request

import com.mango.domain.comment.model.CommentId

data class UpdateCommentRequestModel(
    val commentId: CommentId,
    val text: String?,
)
