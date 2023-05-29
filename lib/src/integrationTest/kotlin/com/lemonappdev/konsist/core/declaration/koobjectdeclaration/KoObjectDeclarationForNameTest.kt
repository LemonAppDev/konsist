package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForNameTest {
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
            .objects()
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/koobjectdeclaration/snippet/forname/".toNormalizedPath(),
        fileName,
    )
}
