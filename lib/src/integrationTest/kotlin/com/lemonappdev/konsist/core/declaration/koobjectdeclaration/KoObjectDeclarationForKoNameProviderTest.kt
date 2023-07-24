package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoNameProviderTest {
    @Test
    fun `object`() {
        // given
        val sut = getSnippetFile("object")
            .objects()
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `companion-object-with-name`() {
        // given
        val sut = getSnippetFile("companion-object-with-name")
            .objects(includeNested = true)
            .first()

        // then
        sut.name shouldBeEqualTo "SampleObject"
    }

    @Test
    fun `companion-object-without-name`() {
        // given
        val sut = getSnippetFile("companion-object-without-name")
            .objects(includeNested = true)
            .first()

        // then
        sut.name shouldBeEqualTo "Companion"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkonameprovider/", fileName)
}
