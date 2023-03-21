package com.lemon.mango.application.model.comment

import com.lemon.mango.domain.comment.model.CommentId

data class UpdateCommentRequestModel(
    val commentId: CommentId,
    val text: String?,
)
