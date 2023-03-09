package com.mango.data.comment

import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.CommentId
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service

@Service
class CommentJpaEntityToCommentMapper {
    operator fun invoke(commentJpaEntity: CommentJpaEntity) = Comment(
        id = CommentId(commentJpaEntity.id),
        creationDate = commentJpaEntity.creationDate,
        taskId = TaskId(commentJpaEntity.taskId),
        creatorId = UserId(commentJpaEntity.ownerId),
        text = commentJpaEntity.text,
    )
}
