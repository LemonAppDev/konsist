package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.task.activity.UpdateTaskCompleteDateActivityFactory
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
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
