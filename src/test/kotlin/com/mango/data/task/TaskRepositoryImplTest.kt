package com.mango.data.task

import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.task.model.Task
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import kotlin.jvm.optionals.getOrNull

class TaskRepositoryImplTest {
    private val taskJpaRepository: TaskJpaRepository = mockk()
    private val taskToTaskJpaEntityMapper: TaskToTaskJpaEntityMapper = mockk()
    private val taskJpaEntityToTaskMapper: TaskJpaEntityToTaskMapper = mockk()

    private val sut = TaskRepositoryImpl(
        taskJpaRepository,
        taskToTaskJpaEntityMapper,
        taskJpaEntityToTaskMapper,
    )

    @Test
    fun `saveTask() saves task`() {
        // given
        val task: Task = mockk()
        val taskJpaEntity: TaskJpaEntity = mockk()
        every { taskToTaskJpaEntityMapper(task) } returns taskJpaEntity
        every { taskJpaRepository.save(taskJpaEntity) } returns taskJpaEntity
        every { taskJpaEntityToTaskMapper(taskJpaEntity) } returns task

        // when
        sut.saveTask(task)

        // then
        verify { taskJpaRepository.save(taskJpaEntity) }
    }

    @Test
    fun `saveTask() returns task`() {
        // given
        val task: Task = mockk()
        val taskJpaEntity: TaskJpaEntity = mockk()
        every { taskToTaskJpaEntityMapper(task) } returns taskJpaEntity
        every { taskJpaRepository.save(taskJpaEntity) } returns taskJpaEntity
        every { taskJpaEntityToTaskMapper(taskJpaEntity) } returns task

        // when
        val actual = sut.saveTask(task)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `deleteTask() deletes task`() {
        // given
        val task: Task = mockk()
        val taskJpaEntity: TaskJpaEntity = mockk()
        every { taskToTaskJpaEntityMapper(task) } returns taskJpaEntity
        justRun { taskJpaRepository.delete(taskJpaEntity) }
        every { taskJpaEntityToTaskMapper(taskJpaEntity) } returns task

        // when
        sut.deleteTask(task)

        // then
        verify { taskJpaRepository.delete(taskJpaEntity) }
    }

    @Test
    fun `getTask() return task when it exist`() {
        // given
        val taskId = getTaskId1()
        val task: Task = mockk()
        val taskJpaEntity: TaskJpaEntity = mockk()
        every { taskJpaRepository.findById(taskId.value).getOrNull() } returns taskJpaEntity
        every { taskJpaEntityToTaskMapper(taskJpaEntity) } returns task

        // when
        val actual = sut.getTask(taskId)

        // then
        actual shouldBeEqualTo task
    }

    @Test
    fun `getTask() return null when it doesn't exist`() {
        // given
        val taskId = getTaskId1()
        every { taskJpaRepository.findById(taskId.value).getOrNull() } returns null

        // when
        val actual = sut.getTask(taskId)

        // then
        actual shouldBeEqualTo null
    }

    @Test
    fun `containsTask() return true when task exist`() {
        // given
        val taskId = getTaskId1()
        every { taskJpaRepository.existsById(taskId.value) } returns true

        // when
        val actual = sut.containsTask(taskId)

        // then
        actual shouldBeEqualTo true
    }

    @Test
    fun `containsTask() return false when task doesn't exist`() {
        // given
        val taskId = getTaskId1()
        every { taskJpaRepository.existsById(taskId.value) } returns false

        // when
        val actual = sut.containsTask(taskId)

        // then
        actual shouldBeEqualTo false
    }
}
