package com.mango.data.activity

import com.mango.domain.activity.ProjectActivity
import com.mango.domain.activity.TaskActivity
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class ActivityRepositoryImplTest {
    private val sut = ActivityRepositoryImpl()

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
