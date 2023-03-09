package com.mango.business.usecase.task.update

import com.mango.business.model.Task
import com.mango.business.model.activity.task.UpdateTaskCompleteDateActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskCompleteDateUseCase(
    private val taskRepository: TaskRepository,
    private val updateTaskCompleteDateActivityFactory: UpdateTaskCompleteDateActivityFactory,
    private val activityRepository: ActivityRepository,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,

) {
    operator fun invoke(taskId: TaskId, isComplete: Boolean, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        if (isComplete && task.completeDate == null) {
            updateCompleteDate(task, date, date)
        } else if (!isComplete && task.completeDate != null) {
            updateCompleteDate(task, date, null)
        }
    }

    private fun updateCompleteDate(task: Task, date: LocalDateTime, completeDate: LocalDateTime?) {
        val oldDate = task.completeDate
        val newTask = task.copy(completeDate = completeDate)

        taskRepository.saveTask(newTask)

        val activity = updateTaskCompleteDateActivityFactory(newTask.id, date, oldDate, newTask.completeDate)
        activityRepository.addActivity(activity)
    }
}
