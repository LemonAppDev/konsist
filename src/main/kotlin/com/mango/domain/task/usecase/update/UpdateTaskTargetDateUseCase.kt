package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskTargetDateUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskTargetDateActivityFactory: com.mango.domain.task.activity.UpdateTaskTargetDateActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
) {
    operator fun invoke(taskId: TaskId, newTargetDate: LocalDateTime, date: LocalDateTime) {
        requireDateIsNowOrLaterUseCase(newTargetDate)
        val task = getTaskOrThrowUseCase(taskId)

        val oldTargetDate = task.targetDate

        if (newTargetDate != oldTargetDate) {
            val newTask = task.copy(targetDate = newTargetDate)

            taskRepository.saveTask(newTask)

            val activity = updateTaskTargetDateActivityFactory(newTask.id, date, oldTargetDate, newTargetDate)
            activityRepository.addActivity(activity)
        }
    }
}
