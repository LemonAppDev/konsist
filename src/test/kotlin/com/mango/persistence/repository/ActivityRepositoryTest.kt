package com.mango.persistence.repository

import com.mango.business.model.activity.task.TaskActivity
import io.mockk.mockk
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class ActivityRepositoryTest {
    private val sut = ActivityRepository()

    @Test
    fun `addActivity() method add new activity to activity list`() {
        // given
        val activity: TaskActivity = mockk()

        // when
        sut.addActivity(activity)

        // then
        sut.activities shouldContain activity
    }
}
