package com.example.mango.data.model

import com.example.mango.data.model.activity.TaskActivityItem
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

internal class TaskTest {
    @Test
    fun `addChild() add child to list`() {
        // given
        val parentTaskId = 1
        val childTaskId = 2
        val creator = mockk<User>()
        val name = "name"
        val projectId = 3

        val sut = Task(
            id = parentTaskId,
            creator = creator,
            name = name,
            projectId = projectId,
        )

        // when
        sut.addChild(childTaskId)

        // then
        sut.allChildren shouldContain childTaskId
    }

    @Test
    fun `addLog() add log to list`() {
        // given
        val parentTaskId = 1
        val creator = mockk<User>()
        val name = "name"
        val projectId = 3
        val log = mockk<TaskActivityItem>()

        val sut = Task(
            id = parentTaskId,
            creator = creator,
            name = name,
            projectId = projectId,
        )

        // when
        sut.addLog(log)

        // then
        sut.allLogs shouldContain log
    }
}
