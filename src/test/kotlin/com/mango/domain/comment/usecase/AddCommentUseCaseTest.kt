package com.mango.domain.comment.usecase

import com.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.mango.domain.comment.CommentFactory
import com.mango.domain.comment.CommentRepository
import com.mango.domain.comment.model.Comment
import com.mango.domain.comment.model.request.AddCommentRequestModel
import com.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.usecase.CheckTaskIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentUseCaseTest {
    private val addCommentActivityUseCase: AddCommentActivityUseCase = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()
    private val commentFactory: CommentFactory = mockk()
    private val commentRepository: CommentRepository = mockk()
    private val checkCommentTextUseCase: CheckCommentTextUseCase = mockk()

    private val sut = AddCommentUseCase(
        addCommentActivityUseCase,
        checkTaskIdUseCase,
        commentFactory,
        commentRepository,
        checkCommentTextUseCase,
    )

    @Test
    fun `saves in repository`() {
        // given
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val text = "comment"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        every { addCommentActivityUseCase(comment, date, text) } returns mockk()

        // when
        sut(addCommentRequestModel)

        // then
        verify { commentRepository.saveComment(comment) }
    }

    @Test
    fun `calls addCommentActivityUseCase`() {
        // given
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val text = "comment"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        every { addCommentActivityUseCase(comment, date, text) } returns mockk()

        // when
        sut(addCommentRequestModel)

        // then
        verify { addCommentActivityUseCase(comment, date, text) }
    }

    @Test
    fun `returns comment`() {
        // given
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val text = "comment"
        val addCommentRequestModel = AddCommentRequestModel(taskId, text)
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        every { addCommentActivityUseCase(comment, date, text) } returns mockk()

        // when
        val actual = sut(addCommentRequestModel)

        // then
        actual shouldBeEqualTo comment
    }
}
