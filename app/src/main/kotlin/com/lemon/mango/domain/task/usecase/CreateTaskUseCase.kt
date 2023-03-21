package com.lemon.mango.domain.task.usecase

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.project.usecase.CheckProjectIdUseCase
import com.lemon.mango.domain.task.TaskFactory
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.model.UserId
import com.lemon.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CreateTaskUseCase(
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val taskFactory: TaskFactory,
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
