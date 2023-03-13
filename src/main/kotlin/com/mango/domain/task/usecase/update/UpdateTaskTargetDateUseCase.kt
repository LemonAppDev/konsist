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
class UpdateTaskTargetDateUseCase(
    private val taskRepository: TaskRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val taskActivityFactory: TaskActivityFactory,
    private val activityRepository: ActivityRepository,
) {
    operator fun invoke(taskId: TaskId, newTargetDate: LocalDateTime, date: LocalDateTime) {
        requireDateIsNowOrLaterUseCase(newTargetDate)
        val task = getTaskOrThrowUseCase(taskId)

        val oldTargetDate = task.targetDate

        if (newTargetDate != oldTargetDate) {
            val newTask = task.copy(targetDate = newTargetDate)

            taskRepository.saveTask(newTask)

            val activity = taskActivityFactory(
                newTask.value,
                date,
                TaskActivityType.UPDATE_TARGET_DATE,
                newTargetDate.toString(),
                oldTargetDate.toString(),
            )
            activityRepository.addTaskActivity(activity)
        }
    }
}
