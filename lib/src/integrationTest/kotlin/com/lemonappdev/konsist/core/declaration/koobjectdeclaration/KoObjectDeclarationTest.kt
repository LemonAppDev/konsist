package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationTest {
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
    fun `data-object`() {
        // given
        val sut = getSnippetFile("data-object")
            .objects()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    @Test
    fun `companion-object-with-name`() {
        // given
        val sut = getSnippetFile("companion-object-with-name")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            sut.hasCompanionModifier() shouldBeEqualTo true
            sut.name shouldBeEqualTo "SampleObject"
        }
    }

    @Test
    fun `companion-object-without-name`() {
        // given
        val sut = getSnippetFile("companion-object-without-name")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            sut.hasCompanionModifier() shouldBeEqualTo true
            sut.name shouldBeEqualTo "Companion"
        }
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/", fileName)
}
