package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistTestForToString {
    @Test
    fun `toString method`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/")
            .toString()

        // then
        sut shouldBeEqualTo """
            $appMainSourceSetDirectory/sample/AppClass.kt
            $appMainSourceSetDirectory/sample/data/AppDataClass.kt
        """.trimIndent()
    }
}
