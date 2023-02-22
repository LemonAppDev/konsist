package com.example.mango.data.model

import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

internal class TaskTest {
    @Test
    fun `subtask is added to subtasks`() {
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
}
