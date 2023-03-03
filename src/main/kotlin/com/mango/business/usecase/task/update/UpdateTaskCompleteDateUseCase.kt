package com.mango.business.usecase.task.update

import com.mango.business.model.Task
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskCompleteDateUseCase(
    private val taskRepository: TaskRepository,
    private val updateTaskCompleteDateActivityFactory: UpdateTaskCompleteDateActivityFactory,
    private val activityRepository: ActivityRepository,
    private val getTaskUseCase: GetTaskUseCase,

) {
    operator fun invoke(taskId: TaskId, isComplete: Boolean, date: LocalDateTime) {
        val task = getTaskUseCase(taskId)

        if (isComplete && task.completeDate == null) {
            updateCompleteDate(task, date, date)
        } else if (!isComplete && task.completeDate != null) {
            updateCompleteDate(task, date, null)
        }
    }

    private fun updateCompleteDate(task: Task, activityDate: LocalDateTime, completeDate: LocalDateTime?) {
        val completedTask = task.copy(completeDate = completeDate)
        taskRepository.updateTask(completedTask)

        val activity = updateTaskCompleteDateActivityFactory(completedTask.id, activityDate)
        activityRepository.addActivity(activity)
    }
}
