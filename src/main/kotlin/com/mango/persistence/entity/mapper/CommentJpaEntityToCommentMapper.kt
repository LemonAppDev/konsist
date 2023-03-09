package com.mango.persistence.entity.mapper

import com.mango.business.model.Comment
import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.persistence.entity.CommentJpaEntity
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
