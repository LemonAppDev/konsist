package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskProjectActivityFactory
import com.mango.business.model.value.ProjectId
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.ProjectRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskProjectUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskProjectActivityFactory: UpdateTaskProjectActivityFactory,
    private val getTaskUseCase: GetTaskUseCase,
    private val projectRepository: ProjectRepository,
) {
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId, date: LocalDateTime) {
        val task = getTaskUseCase(taskId)

        val newProjectExists = projectRepository.containsProject(newProjectId)
        require(newProjectExists) { "Project with id: $newProjectId doesn't exist" }

        val oldProjectId = task.projectId
        val newTask = task.copy(projectId = newProjectId)

        taskRepository.updateTask(newTask)

        val activity = updateTaskProjectActivityFactory(newTask.id, date, oldProjectId, newProjectId)
        activityRepository.addActivity(activity)
    }
}
