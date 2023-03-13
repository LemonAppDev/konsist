package com.mango.data.activity.comment

import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.CommentActivityId
import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.comment.model.CommentId
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
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
