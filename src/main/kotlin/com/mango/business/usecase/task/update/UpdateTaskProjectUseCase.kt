package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.project.GetProjectOrThrowUseCase
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
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
