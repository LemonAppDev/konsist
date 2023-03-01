package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskTargetDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskTargetDateUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskTargetDateActivityFactory: UpdateTaskTargetDateActivityFactory,
) {
    operator fun invoke(taskId: TaskId, newTargetDate: LocalDateTime, date: LocalDateTime) {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        val oldTargetDate = task.targetDate
        val newTask = task.copy(targetDate = newTargetDate)

        taskRepository.updateTask(newTask)

        val activity = updateTaskTargetDateActivityFactory(newTask.id, date, oldTargetDate, newTargetDate)
        activityRepository.addActivity(activity)
    }
}
