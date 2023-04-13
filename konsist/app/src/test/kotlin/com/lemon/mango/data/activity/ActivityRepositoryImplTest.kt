package com.lemon.mango.data.activity

import com.lemon.mango.data.activity.comment.CommentActivityJpaEntity
import com.lemon.mango.data.activity.comment.CommentActivityJpaEntityToCommentActivityMapper
import com.lemon.mango.data.activity.comment.CommentActivityJpaRepository
import com.lemon.mango.data.activity.comment.CommentActivityToCommentActivityJpaEntityMapper
import com.lemon.mango.data.activity.project.ProjectActivityJpaEntity
import com.lemon.mango.data.activity.project.ProjectActivityJpaEntityToProjectActivityMapper
import com.lemon.mango.data.activity.project.ProjectActivityJpaRepository
import com.lemon.mango.data.activity.project.ProjectActivityToProjectActivityJpaEntityMapper
import com.lemon.mango.data.activity.task.TaskActivityJpaEntity
import com.lemon.mango.data.activity.task.TaskActivityJpaEntityToTaskActivityMapper
import com.lemon.mango.data.activity.task.TaskActivityJpaRepository
import com.lemon.mango.data.activity.task.TaskActivityToTaskActivityJpaEntityMapper
import com.lemon.mango.domain.activity.model.CommentActivity
import com.lemon.mango.domain.activity.model.ProjectActivity
import com.lemon.mango.domain.activity.model.TaskActivity
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ActivityRepositoryImplTest {
    private val commentActivityJpaEntityToCommentActivityMapper: CommentActivityJpaEntityToCommentActivityMapper = mockk()
    private val commentActivityJpaRepository: CommentActivityJpaRepository = mockk()
    private val commentActivityToCommentActivityJpaEntityMapper: CommentActivityToCommentActivityJpaEntityMapper = mockk()
    private val projectActivityJpaEntityToProjectActivityMapper: ProjectActivityJpaEntityToProjectActivityMapper = mockk()
    private val projectActivityJpaRepository: ProjectActivityJpaRepository = mockk()
    private val projectActivityToProjectActivityJpaEntityMapper: ProjectActivityToProjectActivityJpaEntityMapper = mockk()
    private val taskActivityJpaEntityToTaskActivityMapper: TaskActivityJpaEntityToTaskActivityMapper = mockk()
    private val taskActivityJpaRepository: TaskActivityJpaRepository = mockk()
    private val taskActivityToTaskActivityJpaEntityMapper: TaskActivityToTaskActivityJpaEntityMapper = mockk()

    private val sut = ActivityRepositoryImpl(
        commentActivityJpaEntityToCommentActivityMapper,
        commentActivityJpaRepository,
        commentActivityToCommentActivityJpaEntityMapper,
        projectActivityJpaEntityToProjectActivityMapper,
        projectActivityJpaRepository,
        projectActivityToProjectActivityJpaEntityMapper,
        taskActivityJpaEntityToTaskActivityMapper,
        taskActivityJpaRepository,
        taskActivityToTaskActivityJpaEntityMapper,
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
    fun `addProjectActivity() saves activity`() {
        // given
        val projectActivity: ProjectActivity = mockk()
        val projectActivityJpaEntity: ProjectActivityJpaEntity = mockk()
        every { projectActivityToProjectActivityJpaEntityMapper(projectActivity) } returns projectActivityJpaEntity
        every { projectActivityJpaRepository.save(projectActivityJpaEntity) } returns projectActivityJpaEntity
        every { projectActivityJpaEntityToProjectActivityMapper(projectActivityJpaEntity) } returns projectActivity

        // when
        sut.addProjectActivity(projectActivity)

        // then
        verify { projectActivityJpaRepository.save(projectActivityJpaEntity) }
    }

    @Test
    fun `addProjectActivity() returns activity`() {
        // given
        val projectActivity: ProjectActivity = mockk()
        val projectActivityJpaEntity: ProjectActivityJpaEntity = mockk()
        every { projectActivityToProjectActivityJpaEntityMapper(projectActivity) } returns projectActivityJpaEntity
        every { projectActivityJpaRepository.save(projectActivityJpaEntity) } returns projectActivityJpaEntity
        every { projectActivityJpaEntityToProjectActivityMapper(projectActivityJpaEntity) } returns projectActivity

        // when
        val actual = sut.addProjectActivity(projectActivity)

        // then
        actual shouldBeEqualTo projectActivity
    }

    @Test
    fun `addTaskActivity() saves activity`() {
        // given
        val taskActivity: TaskActivity = mockk()
        val taskActivityJpaEntity: TaskActivityJpaEntity = mockk()
        every { taskActivityToTaskActivityJpaEntityMapper(taskActivity) } returns taskActivityJpaEntity
        every { taskActivityJpaRepository.save(taskActivityJpaEntity) } returns taskActivityJpaEntity
        every { taskActivityJpaEntityToTaskActivityMapper(taskActivityJpaEntity) } returns taskActivity

        // when
        sut.addTaskActivity(taskActivity)

        // then
        verify { taskActivityJpaRepository.save(taskActivityJpaEntity) }
    }

    @Test
    fun `addTaskActivity() returns activity`() {
        // given
        val taskActivity: TaskActivity = mockk()
        val taskActivityJpaEntity: TaskActivityJpaEntity = mockk()
        every { taskActivityToTaskActivityJpaEntityMapper(taskActivity) } returns taskActivityJpaEntity
        every { taskActivityJpaRepository.save(taskActivityJpaEntity) } returns taskActivityJpaEntity
        every { taskActivityJpaEntityToTaskActivityMapper(taskActivityJpaEntity) } returns taskActivity

        // when
        val actual = sut.addTaskActivity(taskActivity)

        // then
        actual shouldBeEqualTo taskActivity
    }
}
