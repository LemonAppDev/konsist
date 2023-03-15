package com.mango.domain.task.usecase.update

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
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
    private val taskActivityFactory: TaskActivityFactory,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newProjectId: ProjectId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)
        getProjectOrThrowUseCase(newProjectId)

        val oldProjectId = task.projectId

        if (newProjectId != oldProjectId) {
            val newTask = task.copy(projectId = newProjectId)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.id,
                date,
                TaskActivityType.UPDATE_PROJECT,
                newProjectId.value.toString(),
                oldProjectId?.value.toString(),
            )
            activityRepository.addTaskActivity(activity)

            taskRepository.tasks
                .filter { it.parentTaskId == taskId }
                .forEach {
                    taskRepository.saveTask(it.copy(projectId = newProjectId))

                    val childTaskActivity = taskActivityFactory(
                        it.id,
                        date,
                        TaskActivityType.UPDATE_PROJECT,
                        newProjectId.value.toString(),
                        oldProjectId?.value.toString(),
                    )
                    activityRepository.addTaskActivity(childTaskActivity)
                }

            addProjectActivityUseCase(newProjectId, ProjectActivityType.TASK_ADDED, date)
        }
    }
}
