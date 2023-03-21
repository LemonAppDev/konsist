package com.lemon.mango.data.activity.comment

import com.lemon.mango.domain.activity.model.CommentActivity
import com.lemon.mango.domain.activity.model.CommentActivityId
import com.lemon.mango.domain.activity.model.CommentActivityType
import com.lemon.mango.domain.comment.model.CommentId
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class CommentActivityJpaEntityToCommentActivityMapper {
    operator fun invoke(commentActivityJpaEntity: CommentActivityJpaEntity) = CommentActivity(
        id = CommentActivityId(commentActivityJpaEntity.id),
        ownerId = UserId(commentActivityJpaEntity.ownerId),
        type = CommentActivityType.getByValue(commentActivityJpaEntity.type),
        commentId = CommentId(commentActivityJpaEntity.commentId),
        taskId = TaskId(commentActivityJpaEntity.taskId),
        date = commentActivityJpaEntity.date,
        newValue = commentActivityJpaEntity.newValue,
        oldValue = commentActivityJpaEntity.oldValue,
    )
}
