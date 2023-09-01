package com.lemonappdev.konsist.core.declaration.koconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `enum-const-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("enum-const-fully-qualified-name")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass.SAMPLE_CONSTANT_1"
    }

    @Test
    fun `enum-const-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("enum-const-fully-qualified-name-without-package")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleClass.SAMPLE_CONSTANT_1"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstant/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
