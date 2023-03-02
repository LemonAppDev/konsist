package com.mango.business.model.activity.task

import com.mango.business.model.value.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

class AddCommentActivity(
    taskId: TaskId,
    date: LocalDateTime,
    val commentText: String,
) : TaskActivity(taskId, date)

@Service
class AddCommentActivityFactory {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        commentText: String,
    ) = AddCommentActivity(taskId, date, commentText)
}
