package com.mango.business.usecase.task.update

import com.mango.business.common.model.BusinessTestModel
import com.mango.business.model.activity.task.UpdateTaskAssigneeActivity
import com.mango.business.model.activity.task.UpdateTaskAssigneeActivityFactory
import com.mango.business.model.value.TaskId
import com.mango.business.model.value.UserId
import com.mango.business.usecase.task.GetTaskOrThrowUseCase
import com.mango.business.usecase.user.CheckUserIdUseCase
import com.mango.persistence.repository.ActivityRepository
import com.mango.persistence.repository.TaskRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UpdateTaskAssigneeUseCaseTest {
    private val taskRepository: TaskRepository = mockk()
    private val activityRepository: ActivityRepository = mockk()
    private val updateTaskAssigneeActivityFactory: UpdateTaskAssigneeActivityFactory = mockk()
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
        val oldAssigneeId = UserId("old assigneeId")
        val newAssigneeId = UserId("new assigneeId")

        val taskId = TaskId("1")
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        justRun { taskRepository.updateTask(newTask) }
        val date: LocalDateTime = mockk()
        val activity: UpdateTaskAssigneeActivity = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify { taskRepository.updateTask(newTask) }
    }

    @Test
    fun `add activity to activity repository`() {
        // given
        val oldAssigneeId = UserId("old assigneeId")
        val newAssigneeId = UserId("new assigneeId")

        val taskId = TaskId("1")
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        justRun { taskRepository.updateTask(newTask) }
        val date: LocalDateTime = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns mockk()

        val activity: UpdateTaskAssigneeActivity = mockk()
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
        val oldAssigneeId = UserId("assigneeId")
        val newAssigneeId = UserId("assigneeId")

        val taskId = TaskId("1")
        val oldTask = BusinessTestModel.getTask(id = taskId, assigneeId = oldAssigneeId)
        every { getTaskOrThrowUseCase(taskId) } returns oldTask
        val newTask = oldTask.copy(assigneeId = newAssigneeId)
        justRun { checkUserIdUseCase(newAssigneeId) }
        justRun { taskRepository.updateTask(newTask) }
        val date: LocalDateTime = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns mockk()

        val activity: UpdateTaskAssigneeActivity = mockk()
        every { updateTaskAssigneeActivityFactory(taskId, date, oldAssigneeId, newAssigneeId) } returns activity
        justRun { activityRepository.addActivity(activity) }

        // when
        sut(taskId, newAssigneeId, date)

        // then
        verify(exactly = 0) {
            activityRepository.addActivity(activity)
            taskRepository.updateTask(newTask)
        }
    }
}
