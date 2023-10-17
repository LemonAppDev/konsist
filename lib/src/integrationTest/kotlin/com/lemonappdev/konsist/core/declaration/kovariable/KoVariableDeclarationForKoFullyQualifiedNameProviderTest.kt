package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `variable-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("variable-fully-qualified-name")
            .functions()
            .variables
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleVariable"
    }

    @Test
    fun `variable-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("variable-fully-qualified-name-without-package")
            .functions()
            .variables
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "sampleVariable"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
