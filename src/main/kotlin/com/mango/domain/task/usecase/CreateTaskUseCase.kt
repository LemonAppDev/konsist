package com.mango.domain.task.usecase

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.TaskFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val taskFactory: TaskFactory,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val taskActivityFactory: TaskActivityFactory,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val activityRepository: ActivityRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        val creationDate = localDateTimeFactory()

        with(createTaskRequestModel) {
            dueDate?.let { requireDateIsNowOrLaterUseCase(it) }
            targetDate?.let { requireDateIsNowOrLaterUseCase(it) }
            projectId?.let { checkProjectIdUseCase(it) }
            parentTaskId?.let {
                val parentTask = getTaskOrThrowUseCase(it)
                require(parentTask.projectId == projectId) { "Task and parent task are not in the same project" }
            }
            assigneeId?.let { checkUserIdUseCase(it) }
        }

        val task = taskFactory(createTaskRequestModel, creationDate)

        return taskRepository.saveTask(task).also {
            val activity = taskActivityFactory(it.id, creationDate, TaskActivityType.CREATE)
            activityRepository.addTaskActivity(activity)

            it.projectId?.let { projectId ->
                addProjectActivityUseCase(projectId, ProjectActivityType.TASK_ADDED, creationDate)
            }
        }
    }
}
