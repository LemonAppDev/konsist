package com.lemonappdev.konsist.scope

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toCanonicalPaths
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KonsistToStringTest {
    @Test
    fun `toString method`() {
        // given
        val sut = Konsist
            .scopeFromDirectory("$appMainSourceSetDirectory/sample/".toCanonicalPaths().toCanonicalPaths(), absolutePath = true)
            .toString()

        // then
        sut shouldBeEqualTo """
            $appMainSourceSetDirectory/sample/AppClass.kt
            $appMainSourceSetDirectory/sample/data/AppDataClass.kt
        """
            .trimIndent()
            .toCanonicalPaths()
    }
}
