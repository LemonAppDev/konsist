package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.annotations
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `annotation-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleAnnotation"
    }

    /*
    The "SampleAnnotation" contains "Annotation" in name.
    Test makes sure that correct fully qualified name is returned.
     */
    @Test
    fun `annotation-fully-qualified-name-when-other-import-contains-its-name`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-when-other-import-contains-its-name")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.testpackage.Annotation"
    }

    @Test
    fun `annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleAnnotation"
    }

    @Test
    fun `annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package`() {
        // given
        val sut =
            getSnippetFile("annotation-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-with-package")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleAnnotation"
    }

    @Test
    fun `annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package`() {
        // given
        val sut =
            getSnippetFile("annotation-in-file-fully-qualified-name-where-annotation-is-defined-in-the-same-file-without-package")
                .files
                .annotations
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleAnnotation"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotation/snippet/forkofullyqualifiednameprovider/", fileName)
}
