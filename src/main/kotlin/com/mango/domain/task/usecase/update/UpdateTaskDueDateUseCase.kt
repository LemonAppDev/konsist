package com.mango.domain.task.usecase.update

import com.mango.domain.activity.ActivityRepository
import com.mango.domain.activity.TaskActivityFactory
import com.mango.domain.activity.model.TaskActivityType
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskDueDateUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newDueDate: LocalDateTime, date: LocalDateTime) {
        requireDateIsNowOrLaterUseCase(newDueDate)
        val task = getTaskOrThrowUseCase(taskId)

        val oldDueDate = task.dueDate

        if (newDueDate != oldDueDate) {
            val newTask = task.copy(dueDate = newDueDate)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.id,
                date,
                TaskActivityType.UPDATE_DUE_DATE,
                newDueDate.toString(),
                oldDueDate.toString(),
            )
            activityRepository.addTaskActivity(activity)
        }
    }
}
