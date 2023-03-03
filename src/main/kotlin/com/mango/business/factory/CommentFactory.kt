package com.mango.business.factory

import com.mango.business.model.Comment
import com.mango.business.model.request.comment.AddCommentRequestModel
import com.mango.persistence.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class CommentFactory(
    private val uuidFactory: UUIDFactory,
    private val localDateTimeFactory: LocalDateTimeFactory,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        addCommentRequestModel: AddCommentRequestModel,
    ) = Comment(
        uuidFactory.createCommentId(),
        addCommentRequestModel.text,
        addCommentRequestModel.taskId,
        userRepository.getCurrentUser().id,
        localDateTimeFactory(),
    )
}
