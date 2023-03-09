package com.mango.domain.task.usecase

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.usecase.RequireDateIsNowOrLaterUseCase
import com.mango.domain.project.usecase.CheckProjectIdUseCase
import com.mango.domain.task.model.Task
import com.mango.domain.task.model.request.CreateTaskRequestModel
import com.mango.domain.user.usecase.CheckUserIdUseCase
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val createTaskActivityFactory: com.mango.domain.task.activity.CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val taskFactory: com.mango.domain.task.TaskFactory,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    operator fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        val creationDate = localDateTimeFactory()

        with(createTaskRequestModel) {
            dueDate?.let { requireDateIsNowOrLaterUseCase(it) }
            targetDate?.let { requireDateIsNowOrLaterUseCase(it) }
            projectId?.let { checkProjectIdUseCase(it) }
            parentTaskId?.let { checkTaskIdUseCase(it) }
            assigneeId?.let { checkUserIdUseCase(it) }
        }

        val task = taskFactory(createTaskRequestModel, creationDate)

        return taskRepository.saveTask(task).also {
            val activity = createTaskActivityFactory(task.id, creationDate)
            activityRepository.addActivity(activity)
        }
    }
}
