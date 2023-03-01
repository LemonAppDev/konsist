package com.mango.business.common

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import java.time.LocalDateTime

@Suppress("LongParameterList")
object BusinessTestModel {
    fun getTask(
        id: TaskId = TaskId("taskId"),
        name: String = "name",
        ownerId: UserId = UserId("ownerId"),
        creationDate: LocalDateTime = LocalDateTime.now(),
        projectId: ProjectId? = null,
        description: String? = null,
        dueDate: LocalDateTime? = null,
        targetDate: LocalDateTime? = null,
        priority: Priority? = null,
        parentTaskId: TaskId? = null,
        assigneeId: UserId? = null,
        completeDate: LocalDateTime? = null,
    ) = Task(
        id = id,
        name = name,
        ownerId = ownerId,
        creationDate = creationDate,
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
