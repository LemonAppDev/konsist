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
            .files
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "KoScopeForAnnotationTest",
                    "KoScopeForClassTest",
                    "KoScopeForDeclarationTest",
                    "KoScopeForEqualsTest",
                    "KoScopeForFileTest",
                    "KoScopeForFunctionTest",
                    "KoScopeForHashCodeTest",
                    "KoScopeForImportTest",
                    "KoScopeForInterfaceTest",
                    "KoScopeForNamedDeclarationTest",
                    "KoScopeForObjectTest",
                    "KoScopeForPackageTest",
                    "KoScopeForPropertyTest",
                    "KoScopeForTypeAliasTest",
                    "KoScopeTest",
                    "KoScopeCreatorImpl",
                    "KoScopeImpl",
                ),
            )
    }
}
