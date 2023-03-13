package com.mango.domain.activity

import com.mango.domain.task.model.TaskId
import com.mango.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskActivityFactory(
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        taskId: TaskId,
        date: LocalDateTime,
        type: TaskActivityType,
        newValue: String? = null,
        oldValue: String? = null,
    ) = TaskActivity(
        userRepository.getCurrentUser().id,
        type,
        taskId,
        date,
        newValue,
        oldValue,
    )
}
