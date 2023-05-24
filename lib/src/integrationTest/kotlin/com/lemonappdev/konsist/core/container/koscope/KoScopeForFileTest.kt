package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForFileTest {
    @Test
    fun `files`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.container.koscope")

        // then
        sut
            .files()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest.kt",
                    "KoScopeForClassTest.kt",
                    "KoScopeForDeclarationTest.kt",
                    "KoScopeForEqualsTest.kt",
                    "KoScopeForFileTest.kt",
                    "KoScopeForFunctionTest.kt",
                    "KoScopeForHashCodeTest.kt",
                    "KoScopeForImportTest.kt",
                    "KoScopeForInterfaceTest.kt",
                    "KoScopeForNamedDeclarationTest.kt",
                    "KoScopeForObjectTest.kt",
                    "KoScopeForPackageTest.kt",
                    "KoScopeForPropertyTest.kt",
                    "KoScopeForTypeAliasTest.kt",
                    "KoScopeTest.kt",
                    "KoScopeCreatorImpl.kt",
                    "KoScopeImpl.kt",
                ),
            )
    }
}
