package com.mango.business.usecase.comment

import com.mango.business.model.Comment
import com.mango.business.model.activity.task.AddCommentActivityFactory
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.persistence.repository.ActivityRepository
import org.springframework.stereotype.Service

@Suppress("LongParameterList")
@Service
class AddCommentUseCase(
    private val createCommentUseCase: CreateCommentUseCase,
    private val activityRepository: ActivityRepository,
    private val addCommentActivityFactory: AddCommentActivityFactory,
) {
    operator fun invoke(addCommentRequestModel: AddCommentRequestModel): Comment {
        val comment = createCommentUseCase(addCommentRequestModel.taskId, addCommentRequestModel.text)

        return comment.also {
            val activity = addCommentActivityFactory(comment.taskId, comment.creationDate, comment.text)
            activityRepository.addActivity(activity)
        }
    }
}
