package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UpdateTaskNameUseCase(
    private val taskRepository: TaskRepository,
    private val activityRepository: ActivityRepository,
    private val updateTaskNameActivityFactory: com.mango.domain.task.activity.UpdateTaskNameActivityFactory,
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase,
) {
    operator fun invoke(taskId: TaskId, newName: String, date: LocalDateTime) {
        val task = getTaskOrThrowUseCase(taskId)

        val oldName = task.name

        if (newName != oldName) {
            val newTask = task.copy(name = newName)

            taskRepository.saveTask(newTask)

            val activity = updateTaskNameActivityFactory(newTask.id, date, oldName, newName)
            activityRepository.addActivity(activity)
        }
    }
}
