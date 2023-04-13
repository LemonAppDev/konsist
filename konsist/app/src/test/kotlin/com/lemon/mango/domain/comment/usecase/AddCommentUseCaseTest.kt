package com.lemon.mango.domain.comment.usecase

import com.lemon.mango.domain.activity.model.CommentActivityType.ADD_COMMENT
import com.lemon.mango.domain.activity.usecase.AddCommentActivityUseCase
import com.lemon.mango.domain.comment.CommentFactory
import com.lemon.mango.domain.comment.CommentRepository
import com.lemon.mango.domain.comment.model.Comment
import com.lemon.mango.domain.common.model.BusinessTestModel.getCommentId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.task.usecase.CheckTaskIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AddCommentUseCaseTest {
    private val addCommentActivityUseCase: AddCommentActivityUseCase = mockk()
    private val checkCommentTextUseCase: CheckCommentTextUseCase = mockk()
    private val checkTaskIdUseCase: CheckTaskIdUseCase = mockk()
    private val commentFactory: CommentFactory = mockk()
    private val commentRepository: CommentRepository = mockk()

    private val sut = AddCommentUseCase(
        addCommentActivityUseCase,
        checkCommentTextUseCase,
        checkTaskIdUseCase,
        commentFactory,
        commentRepository,
    )

    @Test
    fun `saves in repository`() {
        // given
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val text = "comment"
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        justRun { addCommentActivityUseCase(comment, ADD_COMMENT, date, text) }

        // when
        sut(taskId, text)

        // then
        verify { commentRepository.saveComment(comment) }
    }

    @Test
    fun `adds comment activity`() {
        // given
        val taskId = getTaskId1()
        val commentId = getCommentId1()
        val text = "comment"
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        justRun { addCommentActivityUseCase(comment, ADD_COMMENT, date, text) }

        // when
        sut(taskId, text)

        // then
        verify { addCommentActivityUseCase(comment, ADD_COMMENT, date, text) }
    }

    @Test
    fun `returns comment`() {
        // given
        val commentId = getCommentId1()
        val taskId = getTaskId1()
        val text = "comment"
        val date: LocalDateTime = mockk()
        val comment: Comment = mockk()
        every { comment.id } returns commentId
        every { comment.text } returns text
        every { comment.creationDate } returns date
        justRun { checkTaskIdUseCase(taskId) }
        justRun { checkCommentTextUseCase(text) }
        every { commentFactory(taskId, text) } returns comment
        every { commentRepository.saveComment(comment) } returns comment
        justRun { addCommentActivityUseCase(comment, ADD_COMMENT, date, text) }

        // when
        val actual = sut(taskId, text)

        // then
        actual shouldBeEqualTo comment
    }
}
