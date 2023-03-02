package com.mango.business.model.activity.task

import com.mango.business.model.value.CommentId
import com.mango.business.model.value.TaskId
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
