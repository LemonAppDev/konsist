package com.mango.data.activity.comment

import com.mango.domain.activity.model.CommentActivity
import org.springframework.stereotype.Service

@Service
class CommentActivityToCommentActivityJpaEntityMapper {
    operator fun invoke(commentActivity: CommentActivity) = CommentActivityJpaEntity(
        id = commentActivity.id.value,
        ownerId = commentActivity.ownerId.value,
        type = commentActivity.type.value,
        commentId = commentActivity.commentId.value,
        taskId = commentActivity.taskId.value,
        date = commentActivity.date,
        newValue = commentActivity.newValue,
        oldValue = commentActivity.oldValue,
    )
}
