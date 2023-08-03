package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `function-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("function-fully-qualified-name")
            .functions()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.sampleFunction"
    }

    @Test
    fun `function-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("function-fully-qualified-name-without-package")
            .functions()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleFunction"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
