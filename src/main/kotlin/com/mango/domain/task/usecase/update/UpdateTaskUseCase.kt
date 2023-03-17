package com.mango.domain.task.usecase.update

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.project.model.ProjectId
import com.mango.domain.task.model.Priority
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
@Suppress("detekt.LongParameterList")
class UpdateTaskUseCase(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val updateTaskNameUseCase: UpdateTaskNameUseCase,
    private val updateTaskDescriptionUseCase: UpdateTaskDescriptionUseCase,
    private val updateTaskDueDateUseCase: UpdateTaskDueDateUseCase,
    private val updateTaskTargetDateUseCase: UpdateTaskTargetDateUseCase,
    private val updateTaskPriorityUseCase: UpdateTaskPriorityUseCase,
    private val updateTaskProjectUseCase: UpdateTaskProjectUseCase,
    private val updateTaskParentTaskUseCase: UpdateTaskParentTaskUseCase,
    private val updateTaskAssigneeUseCase: UpdateTaskAssigneeUseCase,
    private val updateTaskCompleteDateUseCase: UpdateTaskCompleteDateUseCase,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
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
