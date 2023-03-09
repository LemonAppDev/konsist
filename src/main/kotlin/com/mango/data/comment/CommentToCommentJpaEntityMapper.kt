package com.mango.data.comment

import com.mango.domain.comment.model.Comment
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
