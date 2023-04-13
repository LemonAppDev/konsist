package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.activity.model.ProjectActivityType
import com.lemon.mango.domain.activity.model.TaskActivityType
import com.lemon.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.task.TaskRepository
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeleteTaskUseCase(
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskRepository: TaskRepository,
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
