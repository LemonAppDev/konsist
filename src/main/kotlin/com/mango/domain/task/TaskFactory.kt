package com.mango.domain.task

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        createTaskRequestModel: CreateTaskRequestModel,
        creationDate: LocalDateTime = localDateTimeFactory(),
        completeDate: LocalDateTime? = null,
    ) = Task(
        uuidFactory.createTaskId(),
        createTaskRequestModel.name,
        userRepository.getCurrentUser().id,
        creationDate,
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
