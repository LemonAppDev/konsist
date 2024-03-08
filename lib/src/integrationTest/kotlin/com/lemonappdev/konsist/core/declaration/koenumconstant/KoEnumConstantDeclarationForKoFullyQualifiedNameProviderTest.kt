package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `enum-const-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("enum-const-fully-qualified-name")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass.SAMPLE_CONSTANT_1"
    }

    @Test
    fun `enum-const-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("enum-const-fully-qualified-name-without-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleClass.SAMPLE_CONSTANT_1"
    }

    @Test
    fun `nested-enum-const-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-enum-const-fully-qualified-name")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface.SampleClass.SAMPLE_CONSTANT_1"
    }

    @Test
    fun `nested-enum-const-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-enum-const-fully-qualified-name-without-package")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleClass.SAMPLE_CONSTANT_1"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
