package com.mango.domain.comment

import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId

interface CommentRepository {
    val comments: List<Comment>

    fun getComment(commentId: CommentId): Comment?
    fun deleteComment(comment: Comment)
    fun saveComment(comment: Comment): Comment
}
