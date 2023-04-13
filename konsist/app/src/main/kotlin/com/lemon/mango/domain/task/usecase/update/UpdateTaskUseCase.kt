package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.model.Priority
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Suppress("detekt.LongParameterList")
class UpdateTaskUseCase(
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val updateTaskAssigneeUseCase: UpdateTaskAssigneeUseCase,
    private val updateTaskCompleteDateUseCase: UpdateTaskCompleteDateUseCase,
    private val updateTaskDescriptionUseCase: UpdateTaskDescriptionUseCase,
    private val updateTaskDueDateUseCase: UpdateTaskDueDateUseCase,
    private val updateTaskNameUseCase: UpdateTaskNameUseCase,
    private val updateTaskParentTaskUseCase: UpdateTaskParentTaskUseCase,
    private val updateTaskPriorityUseCase: UpdateTaskPriorityUseCase,
    private val updateTaskProjectUseCase: UpdateTaskProjectUseCase,
    private val updateTaskTargetDateUseCase: UpdateTaskTargetDateUseCase,
) {
    operator fun invoke(
        taskId: TaskId,
        name: String,
        description: String?,
        dueDate: LocalDateTime?,
        targetDate: LocalDateTime?,
        priority: Priority?,
        projectId: ProjectId?,
        parentTaskId: TaskId?,
        assigneeId: UserId?,
        isCompleted: Boolean,
    ) {
        val task = getTaskOrThrowUseCase(taskId)

        // Single date allows to potentially group changes performed at the same time
        val date = localDateTimeFactory()

        updateTaskNameUseCase(task.id, name, date)
        updateTaskDescriptionUseCase(task.id, description, date)
        updateTaskDueDateUseCase(task.id, dueDate, date)
        updateTaskTargetDateUseCase(task.id, targetDate, date)
        updateTaskPriorityUseCase(task.id, priority, date)
        updateTaskProjectUseCase(task.id, projectId, date)
        updateTaskParentTaskUseCase(task.id, parentTaskId, date)
        updateTaskAssigneeUseCase(task.id, assigneeId, date)
        updateTaskCompleteDateUseCase(task.id, isCompleted, date)
    }
}
