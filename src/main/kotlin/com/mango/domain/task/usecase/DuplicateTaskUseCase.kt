package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.UUIDFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.activity.CreateTaskActivityFactory
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DuplicateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val uuidFactory: UUIDFactory,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId): Task {
        val oldTask = getTaskOrThrowUseCase(taskId)

        val newTaskId = uuidFactory.createTaskId()
        val creationDate = localDateTimeFactory()
        val newTask = oldTask.copy(id = newTaskId, creationDate = creationDate)

        taskRepository.saveTask(newTask)

        val createActivity = createTaskActivityFactory(newTask.id, newTask.creationDate)
        activityRepository.addActivity(createActivity)

        return newTask
    }
}
