package com.sample

import androidx.appcompat.app.AppCompatActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.sequence.withParentClassOf
import com.lemonappdev.konsist.core.verify.assert
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `android activity class name ends with 'Activity'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf<AppCompatActivity>()
            .assert { it.name.endsWith("Activity") }
    }
}
