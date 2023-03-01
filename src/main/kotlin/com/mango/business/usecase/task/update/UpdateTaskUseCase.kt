package com.mango.business.usecase.task.update

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.model.request.UpdateTaskRequestModel
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
@Suppress("detekt.LongParameterList")
class UpdateTaskUseCase(
    private val taskRepository: TaskRepository,
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
) {
    operator fun invoke(updateTaskRequestModel: UpdateTaskRequestModel) {
        val task = taskRepository.getTask(updateTaskRequestModel.taskId)
        requireNotNull(task) { "Task with taskId: ${updateTaskRequestModel.taskId} doesn't exist" }

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
