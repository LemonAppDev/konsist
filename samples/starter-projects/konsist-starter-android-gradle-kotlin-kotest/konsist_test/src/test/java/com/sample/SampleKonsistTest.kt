package com.sample

import androidx.appcompat.app.AppCompatActivity
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withAllParentsOf
import com.lemonappdev.konsist.api.verify.assert
import io.kotest.core.spec.style.FreeSpec
import org.junit.jupiter.api.Test

class SampleKonsistTest : FreeSpec({
    "android activity class name ends with 'Activity'" {
        Konsist
            .scopeFromProject()
            .classes()
            .withAllParentsOf(AppCompatActivity::class)
            .assertTrue { it.name.endsWith("Activity") }
    }
})
