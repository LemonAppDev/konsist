package com.mango.domain.task.usecase.update

import com.mango.domain.activity.TaskActivityType
import com.mango.domain.activity.usecase.UpdateTaskActivityUseCase
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskProjectUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
    private val updateTaskActivityUseCase: UpdateTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        getProjectOrThrowUseCase(newProjectId)

        val oldProjectId = task.projectId

        if (newProjectId != oldProjectId) {
            val newTask = task.copy(projectId = newProjectId)

            taskRepository.saveTask(newTask)

            updateTaskActivityUseCase(
                newTask.value,
                date,
                newProjectId.toString(),
                oldProjectId.toString(),
                TaskActivityType.UPDATE_PROJECT,
            )
        }
    }
}
