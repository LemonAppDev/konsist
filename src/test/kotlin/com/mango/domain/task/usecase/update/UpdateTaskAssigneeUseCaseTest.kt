package com.mango.domain.task.usecase.update

import com.mango.data.activity.ActivityRepository
import com.mango.data.task.TaskRepository
import com.mango.domain.common.model.BusinessTestModel
import com.mango.domain.common.model.BusinessTestModel.getTaskId1
import com.mango.domain.common.model.BusinessTestModel.getUserId1
import com.mango.domain.common.model.BusinessTestModel.getUserId2
import com.mango.domain.task.usecase.GetTaskOrThrowUseCase
import com.mango.domain.user.usecase.CheckUserIdUseCase
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskAssigneeUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskAssigneeActivityFactory: com.mango.domain.task.activity.UpdateTaskAssigneeActivityFactory = mockk()
    private val getTaskOrThrowUseCase: GetTaskOrThrowUseCase = mockk()
    private val checkUserIdUseCase: CheckUserIdUseCase = mockk()

    private val sut = UpdateTaskAssigneeUseCase(
        taskRepository,
        activityRepository,
        updateTaskAssigneeActivityFactory,
        getTaskOrThrowUseCase,
        checkUserIdUseCase,
    )

    @Test
    fun `add task to repository`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId2()

        val taskId = getTaskId1()
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        val activity: com.mango.domain.task.activity.UpdateTaskAssigneeActivity = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify { taskRepository.saveTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId2()

        val taskId = getTaskId1()
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns mockk()

        val activity: com.mango.domain.task.activity.UpdateTaskAssigneeActivity = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify { activityRepository.addActivity(activity) }
    }

    @Test
    fun `do nothing when old value is the same as new value`() {
        // given
        val oldAssigneeId = getUserId1()
        val newAssigneeId = getUserId1()

        val taskId = getTaskId1()
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        every { taskRepository.saveTask(newTask) } returns mockk()
        val date: LocalDateTime = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns mockk()

        val activity: com.mango.domain.task.activity.UpdateTaskAssigneeActivity = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.saveTask(newTask)
        }
    }
}
