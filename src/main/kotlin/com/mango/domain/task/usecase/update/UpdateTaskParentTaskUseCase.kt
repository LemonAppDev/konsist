package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.task.activity.UpdateTaskParentTaskActivityFactory
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskParentTaskUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskParentTaskActivityFactory: UpdateTaskParentTaskActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId, newParentTaskId: TaskId, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        getTaskOrThrowUseCase(newParentTaskId)

        val oldParentTaskId = task.parentTaskId

        if (newParentTaskId != oldParentTaskId) {
            val newTask = task.copy(parentTaskId = newParentTaskId)

            taskRepository.saveTask(newTask)

            val activity = updateTaskParentTaskActivityFactory(newTask.id, date, oldParentTaskId, newParentTaskId)
            activityRepository.addActivity(activity)
        }
    }
}
