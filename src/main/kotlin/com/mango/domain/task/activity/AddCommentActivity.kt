package com.mango.domain.task.activity

import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class AddCommentActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val text: String,
) : TaskActivity(taskId, date)

@Service
class AddCommentActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        commentText: String,
    ) = AddCommentActivity(taskId, date, commentText)
}
