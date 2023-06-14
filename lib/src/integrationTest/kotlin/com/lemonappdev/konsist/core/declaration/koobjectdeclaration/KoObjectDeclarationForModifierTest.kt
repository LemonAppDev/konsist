package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForModifierTest {
    @Test
    fun `data-object`() {
        // given
        val sut = getSnippetFile("data-object")
            .objects()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    @Test
    fun `companion-object`() {
        // given
        val sut = getSnippetFile("companion-object")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasCompanionModifier() shouldBeEqualTo true
    }

    @Test
    fun `object-without-modifiers`() {
        // given
        val sut = getSnippetFile("object-without-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            sut.hasDataModifier() shouldBeEqualTo false
            sut.hasCompanionModifier() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/formodifier/", fileName)
}
