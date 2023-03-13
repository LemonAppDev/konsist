package com.mango.domain.project.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.ProjectActivityFactory
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.ProjectRepository
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.TaskRepository
import org.springframework.stereotype.Service

@Service
class DeleteProjectUseCase(
    private val projectRepository: ProjectRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskRepository: TaskRepository,
    private val projectActivityFactory: ProjectActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(projectId: ProjectId) {
        val project = projectRepository.getProject(projectId)

        project?.let {
            projectRepository.deleteProject(it)

            taskRepository.tasks
                .filter { task -> task.projectId == projectId }
                .forEach { task -> taskRepository.deleteTask(task) }

            val date = localDateTimeFactory()
            val activity = projectActivityFactory(it.id, date, ProjectActivityType.DELETE)
            activityRepository.addProjectActivity(activity)
        }
    }
}
