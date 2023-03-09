package com.mango.data.activity

import com.mango.domain.project.activity.ProjectActivity
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class ActivityRepositoryTest {
    private val sut = ActivityRepository()

    @Test
    fun `addActivity() add new TaskActivity to taskActivities`() {
        // given
        val activity: com.mango.domain.task.activity.TaskActivity = mockk()

        // when
        sut.addActivity(activity)

        // then
        sut.taskActivities shouldContain activity
    }

    @Test
    fun `addActivity() add new ProjectActivity to projectActivities`() {
        // given
        val activity: ProjectActivity = mockk()

        // when
        sut.addActivity(activity)

        // then
        sut.projectActivities shouldContain activity
    }
}
