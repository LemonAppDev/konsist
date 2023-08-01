package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeAliasDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `typealias-without-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-without-visibility-modifiers")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            hasPublicModifier shouldBeEqualTo false
            isPublicOrDefault shouldBeEqualTo true
            hasPrivateModifier shouldBeEqualTo false
            hasProtectedModifier shouldBeEqualTo false
            hasInternalModifier shouldBeEqualTo false
            hasActualModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-public-modifier")
            .typeAliases
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `typealias-is-public-by-default`() {
        // given
        val sut = getSnippetFile("typealias-is-public-by-default")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-private-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-private-modifier")
            .typeAliases
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-internal-modifier")
            .typeAliases
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/", fileName)
}
