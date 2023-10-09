package com.sample

import androidx.appcompat.app.AppCompatActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.verify.assertTrue
import io.kotest.core.spec.style.FreeSpec

class SampleKonsistTest : FreeSpec({
    "android activity class name ends with 'Activity'" {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllParentsOf(AppCompatActivity::class)
            .assertTrue { it.name.endsWith("Activity") }
    }
})
