package com.mango.business.usecase.comment

import com.mango.business.common.model.BusinessTestModel.getTaskId1
import com.mango.business.factory.CommentFactory
import com.mango.business.model.Comment
import com.mango.business.usecase.task.CheckTaskIdUseCase
import com.mango.persistence.repository.CommentRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class CreateCommentUseCaseTest {
    private val commentFactory: CommentFactory = mockk()
    private val commentRepository: CommentRepository = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()

    private val sut = CreateCommentUseCase(
        commentFactory,
        commentRepository,
        checkTaskIdUseCase,
    )

    @Test
    fun `calls checkTaskIdUseCase`() {
        // given
        val taskId = getTaskId1()
        val text = "text"
        justRun { checkTaskIdUseCase(taskId) }
        val comment: Comment = mockk()
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns mockk()

        // when
        sut(taskId, text)

        // then
        verify { checkTaskIdUseCase(taskId) }
    }

    @Test
    fun `throws exception when text is blank`() {
        // given
        val taskId = getTaskId1()
        val blankText = ""
        justRun { checkTaskIdUseCase(taskId) }
        // when
        val actual = { sut(taskId, blankText) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Comment text is blank"
    }

    @Test
    fun `returns comment`() {
        // given
        val taskId = getTaskId1()
        val text = "text"
        justRun { checkTaskIdUseCase(taskId) }
        val comment: Comment = mockk()
        every { commentFactory(taskId, text) } returns comment
        val repositoryComment: Comment = mockk()
        every { commentRepository.saveComment(comment) } returns repositoryComment

        // when
        val actual = sut(taskId, text)

        // then
        actual shouldBe repositoryComment
    }
}
