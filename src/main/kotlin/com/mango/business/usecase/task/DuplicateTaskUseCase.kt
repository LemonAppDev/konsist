package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.UUIDFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class DuplicateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val uuidFactory: UUIDFactory,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(taskId: TaskId): Task {
        val oldTask = taskRepository.getTask(taskId)
        requireNotNull(oldTask) { "Task with id: $taskId doesn't exist" }

        val newTaskId = uuidFactory.createTaskId()
        val creationDate = localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate)

        taskRepository.addTask(newTask)

        val createActivity = createTaskActivityFactory(newTask.id, newTask.creationDate)
        activityRepository.addActivity(createActivity)

        return newTask
    }
}
