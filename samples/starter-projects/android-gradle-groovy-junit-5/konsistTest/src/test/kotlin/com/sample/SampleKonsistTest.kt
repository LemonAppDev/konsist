package com.sample

import androidx.activity.ComponentActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `android activity class name ends with 'Activity'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllParentsOf(ComponentActivity::class)
            .assertTrue { it.name.endsWith("Activity") }
    }
}
