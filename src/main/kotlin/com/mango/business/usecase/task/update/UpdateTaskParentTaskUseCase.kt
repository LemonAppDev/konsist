package com.mango.business.usecase.task.update

import com.mango.business.model.activity.task.UpdateTaskParentTaskActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.usecase.task.GetTaskUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskParentTaskUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskParentTaskActivityFactory: UpdateTaskParentTaskActivityFactory,
    private val getTaskUseCase: GetTaskUseCase,
) {
    operator fun invoke(taskId: TaskId, newParentTaskId: TaskId, date: LocalDateTime) {
        val task = getTaskUseCase(taskId)

        getTaskUseCase(newParentTaskId)

        val oldParentTaskId = task.parentTaskId
        val newTask = task.copy(parentTaskId = newParentTaskId)

        taskRepository.updateTask(newTask)

        val activity = updateTaskParentTaskActivityFactory(newTask.id, date, oldParentTaskId, newParentTaskId)
        activityRepository.addActivity(activity)
    }
}
