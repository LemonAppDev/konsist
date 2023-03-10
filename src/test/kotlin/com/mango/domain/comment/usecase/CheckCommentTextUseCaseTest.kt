package com.mango.domain.comment.usecase

import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CheckCommentTextUseCaseTest {
    private val sut = CheckCommentTextUseCase()

    @Test
    fun `throws exception when text is blank`() {
        // given
        val blankText = ""

        // when
        val actual = { sut(blankText) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment text is blank"
    }
}
