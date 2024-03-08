package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parameter-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parameter-fully-qualified-name")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleClassWithParameter.sampleParameter"
    }

    @Test
    fun `parameter-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("parameter-fully-qualified-name-without-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "SampleClassWithParameter.sampleParameter"
    }

    @Test
    fun `nested-parameter-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-parameter-fully-qualified-name")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleInterface.SampleClassWithParameter.sampleParameter"
    }

    @Test
    fun `nested-parameter-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-parameter-fully-qualified-name-without-package")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut?.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleClassWithParameter.sampleParameter"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/snippet/forkodeclarationfullyqualifiednameprovider/", fileName)
}
