package com.mango.domain.task.usecase

import com.mango.domain.activity.usecase.DeleteTaskActivityUseCase
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val deleteTaskActivityUseCase: DeleteTaskActivityUseCase,
) {
    operator fun invoke(taskId: TaskId) {
        val task = taskRepository.getTask(taskId)

        task?.let {
            taskRepository.deleteTask(it)

            val date = localDateTimeFactory()
            deleteTaskActivityUseCase(it.value, date)
        }
    }
}
