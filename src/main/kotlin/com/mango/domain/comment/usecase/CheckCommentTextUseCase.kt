package com.mango.domain.comment.usecase

import org.springframework.stereotype.Service

@Service
class CheckCommentTextUseCase {
    operator fun invoke(text: String) {
        require(text.isNotBlank()) { "Comment text is blank" }
    }
}
