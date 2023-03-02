package com.mango.business.usecase.task.comment

import com.mango.business.model.Comment
import com.mango.business.model.value.TaskId
import com.mango.persistence.repository.CommentRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class GetAllCommentsUseCase(
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository,
) {
    operator fun invoke(taskId: TaskId): List<Comment> {
        val task = taskRepository.getTask(taskId)
        requireNotNull(task) { "Task with id: $taskId doesn't exist" }

        return commentRepository.comments
            .filter { it.taskId == taskId }
    }
}
