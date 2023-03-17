package com.mango.application.model.comment

import com.mango.domain.comment.model.CommentId

data class UpdateCommentRequestModel(
    val commentId: CommentId,
    val text: String?,
)
