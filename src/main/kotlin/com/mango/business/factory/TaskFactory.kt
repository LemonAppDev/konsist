package com.mango.business.factory

import com.mango.business.model.Priority
import com.mango.business.model.Task
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.persistence.repository.UserRepository
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
