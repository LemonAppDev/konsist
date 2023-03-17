package com.mango.domain.comment.usecase

import com.mango.domain.activity.model.CommentActivityType
import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.CommentFactory
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.task.model.TaskId
import com.mango.domain.task.usecase.CheckTaskIdUseCase
import org.springframework.stereotype.Service

@Service
class AddCommentUseCase(
    private val checkTaskIdUseCase: CheckTaskIdUseCase,
    private val commentFactory: CommentFactory,
    private val commentRepository: CommentRepository,
    private val checkCommentTextUseCase: CheckCommentTextUseCase,
    private val addCommentActivityUseCase: AddCommentActivityUseCase,
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
