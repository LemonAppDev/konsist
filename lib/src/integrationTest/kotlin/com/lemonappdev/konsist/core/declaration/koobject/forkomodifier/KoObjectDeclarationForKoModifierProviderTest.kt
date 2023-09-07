package com.lemonappdev.konsist.core.declaration.koobject.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoObjectDeclarationForKoModifierProviderTest {
    @Test
    fun `object-without-modifiers`() {
        // given
        val sut = getSnippetFile("object-without-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            countModifiers { it.type == "private" } shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
            hasDataModifier shouldBeEqualTo false
            hasCompanionModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-private-and-data-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-private-and-data-modifiers")
            .objects(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            countModifiers { it.type == "private" } shouldBeEqualTo 1
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(DATA) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(PRIVATE, DATA) shouldBeEqualTo true
            hasModifiers(DATA, PRIVATE) shouldBeEqualTo true
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA, PRIVATE) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `object-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .objects(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/forkomodifier/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("object-has-modifiers-and-annotation-with-parameter", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-without-parameter", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-annotation-and-comment", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotations", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-with-angle-brackets", listOf(PRIVATE, DATA)),
        )
    }
}
