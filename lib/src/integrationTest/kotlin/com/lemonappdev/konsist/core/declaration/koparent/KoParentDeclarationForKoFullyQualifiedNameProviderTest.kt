package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parent-of-class-fully-qualified-name-with-import`() {
        // given
        val sut = getSnippetFile("parent-of-class-fully-qualified-name-with-import")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `parent-of-class-fully-qualified-name-without-import-and-with-package`() {
        // given
        val sut = getSnippetFile("parent-of-class-fully-qualified-name-without-import-and-with-package")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentClass"
    }

    @Test
    fun `parent-of-class-fully-qualified-name-without-import-and-package`() {
        // given
        val sut = getSnippetFile("parent-of-class-fully-qualified-name-without-import-and-package")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `nested-parent-of-class-fully-qualified-name-with-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-class-fully-qualified-name-with-package")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface.SampleParentClass"
    }

    @Test
    fun `nested-parent-of-class-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-class-fully-qualified-name-without-package")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleParentClass"
    }

    @Test
    fun `parent-of-interface-fully-qualified-name-with-import`() {
        // given
        val sut = getSnippetFile("parent-of-interface-fully-qualified-name-with-import")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
    }

    @Test
    fun `parent-of-interface-fully-qualified-name-without-import-and-with-package`() {
        // given
        val sut = getSnippetFile("parent-of-interface-fully-qualified-name-without-import-and-with-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentInterface"
    }

    @Test
    fun `parent-of-interface-fully-qualified-name-without-import-and-package`() {
        // given
        val sut = getSnippetFile("parent-of-interface-fully-qualified-name-without-import-and-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `nested-parent-of-interface-fully-qualified-name-with-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-interface-fully-qualified-name-with-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass.SampleParentInterface"
    }

    @Test
    fun `nested-parent-of-interface-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-interface-fully-qualified-name-without-package")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleClass.SampleParentInterface"
    }

    @Test
    fun `parent-of-object-fully-qualified-name-with-import`() {
        // given
        val sut = getSnippetFile("parent-of-object-fully-qualified-name-with-import")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `parent-of-object-fully-qualified-name-without-import-and-with-package`() {
        // given
        val sut = getSnippetFile("parent-of-object-fully-qualified-name-without-import-and-with-package")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentClass"
    }

    @Test
    fun `parent-of-object-fully-qualified-name-without-import-and-package`() {
        // given
        val sut = getSnippetFile("parent-of-object-fully-qualified-name-without-import-and-package")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `nested-parent-of-object-fully-qualified-name-with-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-object-fully-qualified-name-with-package")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface.SampleParentClass"
    }

    @Test
    fun `nested-parent-of-object-fully-qualified-name-without-package`() {
        // given
        val sut = getSnippetFile("nested-parent-of-object-fully-qualified-name-without-package")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleParentClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkofullyqualifiednameprovider/", fileName)
}
