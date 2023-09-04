package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoFullyQualifiedNameProviderTest {
    @Test
    fun `parent-fully-qualified-name`() {
        // given
        val sut = getSnippetFile("parent-fully-qualified-name")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClass"
    }

    @Test
    fun `parent-fully-qualified-name-without-import`() {
        // given
        val sut = getSnippetFile("parent-fully-qualified-name-without-import")
            .classes()
            .parents
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "SampleParentClass"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkofullyqualifiednameprovider/", fileName)
}
