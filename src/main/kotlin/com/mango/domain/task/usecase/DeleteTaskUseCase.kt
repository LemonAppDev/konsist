package com.mango.domain.task.usecase

import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId) {
        val task = taskRepository.getTask(taskId)

        task?.let {
            taskRepository.deleteTask(it)

            val date = localDateTimeFactory()
            addTaskActivityUseCase(it.id, TaskActivityType.DELETE, date)

            taskRepository.tasks
                .filter { task -> task.parentTaskId == taskId }
                .forEach { task ->
                    taskRepository.deleteTask(task)
                    addTaskActivityUseCase(task.id, TaskActivityType.DELETE, date)
                }
        }
    }
}
