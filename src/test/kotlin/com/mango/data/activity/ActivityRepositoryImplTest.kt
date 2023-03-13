package com.mango.data.activity

import com.mango.data.activity.comment.CommentActivityJpaEntity
import com.mango.data.activity.comment.CommentActivityJpaEntityToCommentActivityMapper
import com.mango.data.activity.comment.CommentActivityJpaRepository
import com.mango.data.activity.comment.CommentActivityToCommentActivityJpaEntityMapper
import com.mango.domain.activity.model.CommentActivity
import com.mango.domain.activity.model.ProjectActivity
import com.mango.domain.activity.model.TaskActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class ActivityRepositoryImplTest {
    private val commentActivityJpaRepository: CommentActivityJpaRepository = mockk()
    private val commentActivityJpaEntityToCommentActivityMapper: CommentActivityJpaEntityToCommentActivityMapper = mockk()
    private val commentActivityToCommentActivityJpaEntityMapper: CommentActivityToCommentActivityJpaEntityMapper = mockk()

    private val sut = ActivityRepositoryImpl(
        commentActivityJpaRepository,
        commentActivityJpaEntityToCommentActivityMapper,
        commentActivityToCommentActivityJpaEntityMapper,
    )

    @Test
    fun `addCommentActivity() saves activity`() {
        // given
        val commentActivity: CommentActivity = mockk()
        val commentActivityJpaEntity: CommentActivityJpaEntity = mockk()
        every { commentActivityToCommentActivityJpaEntityMapper(commentActivity) } returns commentActivityJpaEntity
        every { commentActivityJpaRepository.save(commentActivityJpaEntity) } returns commentActivityJpaEntity
        every { commentActivityJpaEntityToCommentActivityMapper(commentActivityJpaEntity) } returns commentActivity

        // when
        sut.addCommentActivity(commentActivity)

        // then
        verify { commentActivityJpaRepository.save(commentActivityJpaEntity) }
    }

    @Test
    fun `addCommentActivity() returns activity`() {
        // given
        val commentActivity: CommentActivity = mockk()
        val commentActivityJpaEntity: CommentActivityJpaEntity = mockk()
        every { commentActivityToCommentActivityJpaEntityMapper(commentActivity) } returns commentActivityJpaEntity
        every { commentActivityJpaRepository.save(commentActivityJpaEntity) } returns commentActivityJpaEntity
        every { commentActivityJpaEntityToCommentActivityMapper(commentActivityJpaEntity) } returns commentActivity

        // when
        val actual = sut.addCommentActivity(commentActivity)

        // then
        actual shouldBeEqualTo commentActivity
    }

    @Test
    fun `addActivity() add new TaskActivity to taskActivities`() {
        // given
        val activity: TaskActivity = mockk()

        // when
        sut.addTaskActivity(activity)

        // then
        sut.taskActivities shouldContain activity
    }

    @Test
    fun `addActivity() add new ProjectActivity to projectActivities`() {
        // given
        val activity: ProjectActivity = mockk()

        // when
        sut.addProjectActivity(activity)

        // then
        sut.projectActivities shouldContain activity
    }
}
