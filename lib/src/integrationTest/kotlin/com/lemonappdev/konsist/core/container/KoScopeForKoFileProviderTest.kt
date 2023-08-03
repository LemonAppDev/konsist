package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoFileProviderTest {
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
                    "KoScopeForKoAnnotationProviderTest",
                    "KoScopeForKoClassProviderTest",
                    "KoScopeForKoDeclarationProviderTest",
                    "KoScopeForKoFileProviderTest",
                    "KoScopeForKoFunctionProviderTest",
                    "KoScopeForKoImportProviderTest",
                    "KoScopeForKoInterfaceProviderTest",
                    "KoScopeForKoObjectProviderTest",
                    "KoScopeForKoPackagesProviderTest",
                    "KoScopeForKoPropertyProviderTest",
                    "KoScopeForKoTypeAliasProviderTest",
                    "KoScopeTest",
                    "KoScopeCreatorCore",
                    "KoScopeCore",
                ),
            )
    }

    @Test
    fun `scope-has-files`() {
        // given
        val sut = Konsist.scopeFromPackage("com.lemonappdev.konsist.core.container")

        // then
        assertSoftly(sut) {
            numFiles shouldBeEqualTo 15
            hasFiles() shouldBeEqualTo true
            hasFiles("KoScopeTest") shouldBeEqualTo true
            hasFiles("OtherTest") shouldBeEqualTo false
            hasFiles("KoScopeTest", "KoScopeCreatorTest") shouldBeEqualTo true
            hasFiles("KoScopeTest", "OtherTest") shouldBeEqualTo false
        }
    }
}
