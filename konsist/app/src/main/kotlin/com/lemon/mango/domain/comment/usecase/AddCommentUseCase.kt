package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.domain.activity.model.CommentActivityType
import com.lemon.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.lemon.mango.domain.comment.CommentFactory
import com.lemon.mango.domain.comment.CommentRepository
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.task.model.TaskId
import com.lemon.mango.domain.task.usecase.CheckTaskIdUseCase
import org.springframework.stereotype.Service

@Service
class AddCommentUseCase(
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
    private val checkCommentTextUseCase: CheckCommentTextUseCase,
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val commentFactory: CommentFactory,
    private val commentRepository: CommentRepository,
) {
    operator fun invoke(taskId: TaskId, text: String): Comment {
        checkTaskIdUseCase(taskId)
        checkCommentTextUseCase(text)

        val comment = commentFactory(taskId, text)

        return commentRepository.saveComment(comment).also {
            addCommentActivityUseCase(comment, CommentActivityType.ADD_COMMENT, comment.creationDate, comment.text)
        }
    }
}
