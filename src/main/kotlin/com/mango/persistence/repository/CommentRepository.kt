package com.mango.persistence.repository

import com.mango.business.model.Comment
import com.mango.business.model.value.CommentId
import org.springframework.stereotype.Service

@Service
class CommentRepository {
    private val _comments = mutableListOf<Comment>()

    val comments: List<Comment> get() = _comments

    fun getComment(commentId: CommentId) = _comments.firstOrNull { it.id == commentId }

    fun addComment(comment: Comment) {
        _comments.add(comment)
    }

    fun deleteComment(comment: Comment) {
        _comments.remove(comment)
    }

    fun updateComment(comment: Comment) {
        _comments.removeIf { it.id == comment.id }
        _comments.add(comment)
    }
}
