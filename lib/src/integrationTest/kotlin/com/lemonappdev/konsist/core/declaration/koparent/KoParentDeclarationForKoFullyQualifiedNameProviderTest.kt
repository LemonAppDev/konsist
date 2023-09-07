package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parent-of-class-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("parent-of-class-fully-qualified-name")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `parent-of-class-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("parent-of-class-fully-qualified-name-without-import")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    @Test
    fun `parent-of-interface-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("parent-of-interface-fully-qualified-name")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentInterface"
    }

    @Test
    fun `parent-of-interface-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("parent-of-interface-fully-qualified-name-without-import")
            .interfaces()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentInterface"
    }

    @Test
    fun `parent-of-object-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("parent-of-object-fully-qualified-name")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `parent-of-object-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("parent-of-object-fully-qualified-name-without-import")
            .objects()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkofullyqualifiednameprovider/", fileName)
}
