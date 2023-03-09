package com.mango.domain.comment.usecase

import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.task.TaskRepository
import com.mango.domain.task.model.TaskId
import org.springframework.stereotype.Service

@Service
class GetCommentsUseCase(
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
