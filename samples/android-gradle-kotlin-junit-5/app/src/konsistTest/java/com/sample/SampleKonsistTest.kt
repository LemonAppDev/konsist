package com.sample

import androidx.appcompat.app.AppCompatActivity
import com.lemonappdev.konsist.core.check.assert
import com.lemonappdev.konsist.core.declaration.KoScope
import com.lemonappdev.konsist.core.ext.withParentClassOf
import org.junit.jupiter.api.Test

class SampleKonsistTest {
    @Test
    fun `android activity class name ends with 'Activity'`() {
        KoScope
            .fromProjectFiles()
            .classes()
            .withParentClassOf<AppCompatActivity>()
            .assert { it.name.endsWith("Activity") }
    }
}
