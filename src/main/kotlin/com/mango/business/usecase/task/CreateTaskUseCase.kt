package com.mango.business.usecase.task

import com.mango.business.factory.LocalDateTimeFactory
import com.mango.business.factory.TaskFactory
import com.mango.business.model.Task
import com.mango.business.model.activity.task.CreateTaskActivityFactory
import com.mango.business.model.request.task.CreateTaskRequestModel
import com.mango.business.usecase.common.RequireDateIsNowOrLaterUseCase
import com.mango.business.usecase.project.CheckProjectIdUseCase
import com.mango.business.usecase.user.CheckUserIdUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class CreateTaskUseCase(
    private val taskRepository: TaskRepository,
    private val createTaskActivityFactory: CreateTaskActivityFactory,
    private val activityRepository: ActivityRepository,
    private val taskFactory: TaskFactory,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val checkUserIdUseCase: CheckUserIdUseCase,
    private val checkProjectIdUseCase: CheckProjectIdUseCase,
    private val requireDateIsNowOrLaterUseCase: RequireDateIsNowOrLaterUseCase,
    private val localDateTimeFactory: LocalDateTimeFactory,
) {
    fun invoke(createTaskRequestModel: CreateTaskRequestModel): Task {
        val creationDate = localDateTimeFactory()

        with(createTaskRequestModel) {
            dueDate?.let { requireDateIsNowOrLaterUseCase(it, creationDate) }
            targetDate?.let { requireDateIsNowOrLaterUseCase(it, creationDate) }
            projectId?.let { checkProjectIdUseCase(it) }
            parentTaskId?.let { checkTaskIdUseCase(it) }
            assigneeId?.let { checkUserIdUseCase(it) }
        }

        val task = taskFactory(createTaskRequestModel, creationDate)

        taskRepository.addTask(task)

        val activity = createTaskActivityFactory(task.id, creationDate)
        activityRepository.addActivity(activity)

        return task
    }
}
