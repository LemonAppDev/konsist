package com.lemon.mango.domain.task.usecase.update

import com.lemon.mango.data.task.TaskRepositoryImpl
import com.lemon.mango.domain.activity.model.TaskActivityType.UPDATE_ASSIGNEE
import com.lemon.mango.domain.activity.usecase.AddTaskActivityUseCase
import com.lemon.mango.domain.common.model.BusinessTestModel.getTask
import com.lemon.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId1
import com.lemon.mango.domain.common.model.BusinessTestModel.getUserId2
import com.lemon.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.lemon.mango.domain.user.usecase.CheckUserIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskAssigneeUseCaseTest {
    private val addTaskActivityUseCase: AddTaskActivityUseCase = mockk()
    private val checkUserIdUseCase: CheckUserIdUseCase = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val taskRepository: TaskRepositoryImpl = mockk()

    private val sut = UpdateTaskAssigneeUseCase(
        addTaskActivityUseCase,
        checkUserIdUseCase,
        getTaskOrThrowUseCase,
        taskRepository,
    )

    @Test
    fun `add task to repository`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId2()

        val taskId = getTaskId1()
        val oldTask = getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_ASSIGNEE,
                date,
                newAssigneeId.value.toString(),
                oldAssigneeId.value.toString(),
            )
        }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add task activity to repository`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId2()

        val taskId = getTaskId1()
        val oldTask = getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_ASSIGNEE,
                date,
                newAssigneeId.value.toString(),
                oldAssigneeId.value.toString(),
            )
        }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify {
            addTaskActivityUseCase(
                taskId,
                UPDATE_ASSIGNEE,
                date,
                newAssigneeId.value.toString(),
                oldAssigneeId.value.toString(),
            )
        }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId1()

        val taskId = getTaskId1()
        val oldTask = getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        justRun {
            addTaskActivityUseCase(
                taskId,
                UPDATE_ASSIGNEE,
                date,
                newAssigneeId.value.toString(),
                oldAssigneeId.value.toString(),
            )
        }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify(exactly = 0) {
            taskRepository.saveTask(newTask)
            addTaskActivityUseCase(
                taskId,
                UPDATE_ASSIGNEE,
                date,
                newAssigneeId.value.toString(),
                oldAssigneeId.value.toString(),
            )
        }
    }
}
