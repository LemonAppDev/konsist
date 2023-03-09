package com.mango.persistence.entity.mapper

import com.mango.business.model.Comment
import com.mango.persistence.entity.CommentJpaEntity
import org.springframework.stereotype.Service

@Service
class CommentToCommentJpaEntityMapper {
    operator fun invoke(comment: Comment) = CommentJpaEntity(
        id = comment.id.value,
        creationDate = comment.creationDate,
        ownerId = comment.creatorId.value,
        text = comment.text,
        taskId = comment.taskId.value,
    )
}
