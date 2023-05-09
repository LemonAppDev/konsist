package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.util.PathProvider.applicationMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForToString {
    @Test
    fun `toString method`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$applicationMainSourceSetDirectory/sample/")
            .toString()

        // then
        sut shouldBeEqualTo """
            $applicationMainSourceSetDirectory/sample/AppClass.kt
            $applicationMainSourceSetDirectory/sample/data/AppDataClass.kt
        """.trimIndent()
    }
}
