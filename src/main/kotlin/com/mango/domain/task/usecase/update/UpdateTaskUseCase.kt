package com.mango.domain.task.usecase.update

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.model.request.UpdateTaskRequestModel
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service

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
    operator fun invoke(updateTaskRequestModel: UpdateTaskRequestModel) {
        val task = getTaskOrThrowUseCase(updateTaskRequestModel.taskId)

        val date = localDateTimeFactory()

        with(updateTaskRequestModel) {
            name?.let { updateTaskNameUseCase(task.id, it, date) }
            description?.let { updateTaskDescriptionUseCase(task.id, it, date) }
            dueDate?.let { updateTaskDueDateUseCase(task.id, it, date) }
            targetDate?.let { updateTaskTargetDateUseCase(task.id, it, date) }
            priority?.let { updateTaskPriorityUseCase(task.id, it, date) }
            projectId?.let { updateTaskProjectUseCase(task.id, it, date) }
            parentTaskId?.let { updateTaskParentTaskUseCase(task.id, it, date) }
            assigneeId?.let { updateTaskAssigneeUseCase(task.id, it, date) }
            updateTaskCompleteDateUseCase(task.id, isCompleted, date)
        }
    }
}
