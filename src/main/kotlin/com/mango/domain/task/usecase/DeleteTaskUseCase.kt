package com.mango.domain.task.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, date: LocalDateTime? = null) {
        val task = taskRepository.getTask(taskId)

        task?.let {
            val deletingDate = date ?: localDateTimeFactory()
            delete(it, deletingDate)
        }
    }

    private fun delete(task: Task, date: LocalDateTime? = null) {
        taskRepository.deleteTask(task)

        addTaskActivityUseCase(task.id, TaskActivityType.DELETE, date)

        task.projectId?.let { addProjectActivityUseCase(it, ProjectActivityType.TASK_REMOVED, date, task.id.toString()) }

        taskRepository.tasks
            .filter { newTask -> newTask.parentTaskId == task.id }
            .forEach { newTask -> delete(newTask, date) }
    }
}
