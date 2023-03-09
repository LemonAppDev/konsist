package com.mango.domain.task.activity

import com.mango.domain.comment.model.CommentId
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class DeleteCommentActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val commentId: CommentId,
) : TaskActivity(taskId, date)

@Service
class DeleteCommentActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        commentId: CommentId,
    ) = DeleteCommentActivity(taskId, date, commentId)
}
