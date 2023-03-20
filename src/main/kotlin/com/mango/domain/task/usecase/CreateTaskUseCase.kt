package com.mango.domain.task.usecase

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.model.ProjectId
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.TaskFactory
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.user.model.UserId
import com.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateTaskUseCase(
    private val taskFactory: TaskFactory,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
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

        return saveTaskUseCase(task, creationDate)
    }
}
