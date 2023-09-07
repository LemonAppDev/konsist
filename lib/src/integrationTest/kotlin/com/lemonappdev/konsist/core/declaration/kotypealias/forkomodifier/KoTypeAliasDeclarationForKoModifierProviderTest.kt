package com.lemonappdev.konsist.core.declaration.kotypealias.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeAliasDeclarationForKoModifierProviderTest {
    @Test
    fun `typealias-without-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-without-modifiers")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            countModifiers { it.type == "private" } shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN) shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN, KoModifier.DATA) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `typealias-modifiers`(
        fileName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(PRIVATE)
            numModifiers shouldBeEqualTo 1
            countModifiers { it.type == "private" } shouldBeEqualTo 1
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypealias/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("typealias-has-modifiers-and-annotation-with-parameter"),
            arguments("typealias-has-modifiers-and-annotation-without-parameter"),
            arguments("typealias-has-modifiers-annotation-and-comment"),
            arguments("typealias-has-modifiers-and-annotations"),
            arguments("typealias-has-modifiers-and-annotation-with-angle-brackets"),
        )
    }
}
