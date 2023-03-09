package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.GetProjectOrThrowUseCase
import com.mango.domain.task.activity.UpdateTaskProjectActivityFactory
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskProjectUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskProjectActivityFactory: UpdateTaskProjectActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val getProjectOrThrowUseCase: GetProjectOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        getProjectOrThrowUseCase(newProjectId)

        val oldProjectId = task.projectId

        if (newProjectId != oldProjectId) {
            val newTask = task.copy(projectId = newProjectId)

            taskRepository.saveTask(newTask)

            val activity = updateTaskProjectActivityFactory(newTask.id, date, oldProjectId, newProjectId)
            activityRepository.addActivity(activity)
        }
    }
}
