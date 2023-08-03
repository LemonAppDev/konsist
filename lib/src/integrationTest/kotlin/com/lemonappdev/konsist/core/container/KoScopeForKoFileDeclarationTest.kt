package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoFileDeclarationTest {
    @Test
    fun `scope-files`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.container")

        // then
        sut
            .files
            .map { it.name }
            .shouldBeEqualTo(
                listOf(
                    "KoScopeCreatorTest",
                    "KoScopeForKoAnnotationDeclarationTest",
                    "KoScopeForKoClassDeclarationTest",
                    "KoScopeForKoDeclarationTest",
                    "KoScopeForKoFileDeclarationTest",
                    "KoScopeForKoFunctionDeclarationTest",
                    "KoScopeForKoImportDeclarationTest",
                    "KoScopeForKoInterfaceDeclarationTest",
                    "KoScopeForKoObjectDeclarationTest",
                    "KoScopeForKoPackagesDeclarationTest",
                    "KoScopeForKoPropertyDeclarationTest",
                    "KoScopeForKoTypeAliasDeclarationTest",
                    "KoScopeTest",
                    "KoScopeCore",
                    "KoScopeCreatorCore",
                ),
            )
    }
}
