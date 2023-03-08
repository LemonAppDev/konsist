package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskDueDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.common.RequireDateIsNowOrLaterUseCase
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDueDateUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskDueDateActivityFactory: UpdateTaskDueDateActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
) {
    operator fun invoke(taskId: TaskId, newDueDate: LocalDateTime, date: LocalDateTime) {
        requireDateIsNowOrLaterUseCase(newDueDate)
        val task = getTaskOrThrowUseCase(taskId)

        val oldDueDate = task.dueDate

        if (newDueDate != oldDueDate) {
            val newTask = task.copy(dueDate = newDueDate)

            taskRepository.updateTask(newTask)

            val activity = updateTaskDueDateActivityFactory(newTask.id, date, oldDueDate, newDueDate)
            activityRepository.addActivity(activity)
        }
    }
}
