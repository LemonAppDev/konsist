package com.sample

import androidx.appcompat.app.AppCompatActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.verify.assert
import org.junit.Test

class SampleKonsistTest {
    @Test
    fun `android activity class name ends with 'Activity'`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withParentClassOf(AppCompatActivity::class)
            .assert { it.name.endsWith("Activity") }
    }
}
