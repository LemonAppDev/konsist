package com.mango.business.usecase.comment

import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.CommentRepository
import com.mango.persistence.repository.TaskRepository
import org.springframework.stereotype.Service

@Suppress("LongParameterList")
@Service
class AddCommentUseCase(
    private val commentFactory: CommentFactory,
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository,
    private val activityRepository: ActivityRepository,
    private val addCommentActivityFactory: AddCommentActivityFactory,
) {
    operator fun invoke(addCommentRequestModel: AddCommentRequestModel): Comment {
        val task = taskRepository.getTask(addCommentRequestModel.taskId)
        requireNotNull(task) { "Task doesn't exist id: ${addCommentRequestModel.taskId}" }

        val comment = commentFactory(addCommentRequestModel)

        commentRepository.addComment(comment)

        val activity = addCommentActivityFactory(task.id, comment.creationDate, comment.text)
        activityRepository.addActivity(activity)

        return comment
    }
}
