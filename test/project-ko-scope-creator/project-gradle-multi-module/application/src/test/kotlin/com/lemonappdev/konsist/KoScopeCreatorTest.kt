package com.lemonappdev.konsist

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeCreatorTest {
    @Test
    fun `aaaa`() {
        Konsist
            .scopeFromProject()
            .files()
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "com/lemonappdev/konsist/api/ext/declaration/KoComplexDeclarationExt.kt",
                    "com/lemonappdev/konsist/api/ext/declaration/KoParameterDeclarationExt.kt",
                    "com/lemonappdev/sample/data/AppDataClassTest.kt",
                    "com/lenonappdev/konsist/KoScopeCreatorTest.kt",
                ),
            )
    }
}
