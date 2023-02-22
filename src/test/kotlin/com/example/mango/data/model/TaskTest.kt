package com.example.mango.data.model

import com.example.mango.data.model.activity.TaskActivityItem
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

internal class TaskTest {
    @Test
    fun `addSubtask() add subtask to list`() {
        // given
        val parentId = 1
        val subtaskId = 2
        val creator = mockk<User>()
        val name = "name"
        val projectId = 3

        val sut = Task(
            id = parentId,
            creator = creator,
            name = name,
            projectId = projectId,
        )

        // when
        sut.addSubtask(subtaskId)

        // then
        sut.allSubtasks shouldContain subtaskId
    }

    @Test
    fun `addLog() add log to list`() {
        // given
        val parentId = 1
        val creator = mockk<User>()
        val name = "name"
        val projectId = 3
        val log = mockk<TaskActivityItem>()

        val sut = Task(
            id = parentId,
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
