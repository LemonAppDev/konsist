package com.lemon.mango.domain.task

import com.lemon.mango.domain.common.LocalDateTimeFactory
import com.lemon.mango.domain.common.UUIDFactory
import com.lemon.mango.domain.project.model.ProjectId
import com.lemon.mango.domain.task.model.Priority
import com.lemon.mango.domain.task.model.Task
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.user.UserRepository
import com.lemon.mango.domain.user.model.UserId
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Suppress("detekt.LongParameterList")
@Service
class TaskFactory(
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
    private val uuidFactory: UUIDFactory,
) {
    operator fun invoke(
        name: String,
        description: String?,
        dueDate: LocalDateTime?,
        targetDate: LocalDateTime?,
        priority: Int?,
        projectId: ProjectId?,
        parentTaskId: TaskId?,
        assigneeId: UserId?,
        creationDate: LocalDateTime = localDateTimeFactory(),
        completeDate: LocalDateTime? = null,
    ) = Task(
        uuidFactory.createTaskId(),
        name,
        userRepository.getCurrentUser().id,
        creationDate,
        projectId,
        description,
        dueDate,
        targetDate,
        Priority.getByValue(priority),
        parentTaskId,
        assigneeId,
        completeDate,
    )
}
