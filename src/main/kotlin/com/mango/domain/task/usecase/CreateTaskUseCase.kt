package com.mango.domain.task.usecase

import com.mango.domain.activity.model.ProjectActivityType
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.activity.usecase.AddProjectActivityUseCase
import com.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.TaskFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import com.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val taskFactory: TaskFactory,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val addTaskActivityUseCase: AddTaskActivityUseCase,
    private val addProjectActivityUseCase: AddProjectActivityUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    @Suppress("detekt.LongParameterList")
    operator fun invoke(
        name: String,
        description: String?,
        dueDate: LocalDateTime?,
        targetDate: LocalDateTime?,
        priority: Int?,
        projectId: ProjectId?,
        parentTaskId: TaskId?,
        assigneeId: UserId?,
    ): Task {
        val creationDate = localDateTimeFactory()

        dueDate?.let { requireDateIsNowOrLaterUseCase(it) }
        targetDate?.let { requireDateIsNowOrLaterUseCase(it) }
        projectId?.let { checkProjectIdUseCase(it) }
        parentTaskId?.let {
            val parentTask = getTaskOrThrowUseCase(it)
            require(parentTask.projectId == projectId) { "Task and parent task are not in the same project" }
        }
        assigneeId?.let { checkUserIdUseCase(it) }

        val task = taskFactory(
            name,
            description,
            dueDate,
            targetDate,
            priority,
            projectId,
            parentTaskId,
            assigneeId,
            creationDate,
        )

        return taskRepository.saveTask(task).also {
            addTaskActivityUseCase(it.id, TaskActivityType.CREATE, creationDate)

            it.projectId?.let { projectId ->
                addProjectActivityUseCase(projectId, ProjectActivityType.TASK_ADDED, creationDate, it.id.toString())
            }
        }
    }
}
