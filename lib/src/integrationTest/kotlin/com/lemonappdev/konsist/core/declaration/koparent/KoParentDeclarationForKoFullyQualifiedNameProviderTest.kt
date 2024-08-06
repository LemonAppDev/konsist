package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parent-from-import-of-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-import-of-class-fully-qualified-name")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `nested-parent-from-import-of-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-parent-from-import-of-class-fully-qualified-name")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedClass"
    }

    @Test
    fun `parent-from-file-with-package-of-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-with-package-of-class-fully-qualified-name")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentClass"
    }

    @Test
    fun `parent-from-file-without-package-of-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-without-package-of-class-fully-qualified-name")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `external-parent-of-class-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("external-parent-of-class-fully-qualified-name")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.externalsample.SampleExternalInterface"
    }

    @Test
    fun `nested-parent-of-class-fully-qualified-name-with-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-class-fully-qualified-name-with-package")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface.SampleParentClass"
    }

    @Test
    fun `nested-parent-of-class-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-class-fully-qualified-name-without-package")
                .classes()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleParentClass"
    }

    @Test
    fun `parent-from-import-of-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-import-of-interface-fully-qualified-name")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
    }

    @Test
    fun `nested-parent-from-import-of-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-parent-from-import-of-interface-fully-qualified-name")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterfaceWithNestedDeclarations.SampleNestedInterface"
    }

    @Test
    fun `parent-from-file-with-package-of-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-with-package-of-interface-fully-qualified-name")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentInterface"
    }

    @Test
    fun `parent-from-file-without-package-of-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-without-package-of-interface-fully-qualified-name")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `external-parent-of-interface-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("external-parent-of-interface-fully-qualified-name")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.externalsample.SampleExternalInterface"
    }

    @Test
    fun `nested-parent-of-interface-fully-qualified-name-with-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-interface-fully-qualified-name-with-package")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleClass.SampleParentInterface"
    }

    @Test
    fun `nested-parent-of-interface-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-interface-fully-qualified-name-without-package")
                .interfaces()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleClass.SampleParentInterface"
    }

    @Test
    fun `parent-from-import-of-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-import-of-object-fully-qualified-name")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `nested-parent-from-import-of-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("nested-parent-from-import-of-object-fully-qualified-name")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClassWithNestedDeclarations.SampleNestedClass"
    }

    @Test
    fun `parent-from-file-with-package-of-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-with-package-of-object-fully-qualified-name")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleParentClass"
    }

    @Test
    fun `parent-from-file-without-package-of-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("parent-from-file-without-package-of-object-fully-qualified-name")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `external-parent-of-object-fully-qualified-name`() {
        // given
        val sut =
            getSnippetFile("external-parent-of-object-fully-qualified-name")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.externalsample.SampleExternalInterface"
    }

    @Test
    fun `nested-parent-of-object-fully-qualified-name-with-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-object-fully-qualified-name-with-package")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleInterface.SampleParentClass"
    }

    @Test
    fun `nested-parent-of-object-fully-qualified-name-without-package`() {
        // given
        val sut =
            getSnippetFile("nested-parent-of-object-fully-qualified-name-without-package")
                .objects()
                .parents()
                .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleInterface.SampleParentClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkofullyqualifiednameprovider/", fileName)
}
