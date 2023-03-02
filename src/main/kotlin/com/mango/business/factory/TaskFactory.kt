package com.mango.business.factory

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.request.CreateTaskRequestModel
import com.mango.business.model.value.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(
        createTaskRequestModel: CreateTaskRequestModel,
        ownerId: UserId,
        completeDate: LocalDateTime? = null,
    ) = Task(
        uuidFactory.createTaskId(),
        createTaskRequestModel.name,
        ownerId,
        localDateTimeFactory(),
        createTaskRequestModel.projectId,
        createTaskRequestModel.description,
        createTaskRequestModel.dueDate,
        createTaskRequestModel.targetDate,
        Priority.getByValue(createTaskRequestModel.priority),
        createTaskRequestModel.parentTaskId,
        createTaskRequestModel.assigneeId,
        completeDate,
    )
}
