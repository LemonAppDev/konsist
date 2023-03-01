package com.mango.business.factory

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Suppress("detekt.LongParameterList")
class TaskFactory {
    operator fun invoke(
        id: TaskId,
        name: String,
        ownerId: UserId,
        creationDate: LocalDateTime,
        projectId: ProjectId? = null,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priority: Priority? = null,
        parentTaskId: TaskId? = null,
        assigneeId: UserId? = null,
        completeDate: LocalDateTime? = null,
    ) = Task(
        id,
        name,
        ownerId,
        creationDate,
        projectId,
        description,
        dueDate,
        targetDate,
        priority,
        parentTaskId,
        assigneeId,
        completeDate,
    )
}
