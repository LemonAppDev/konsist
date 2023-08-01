package com.lemonappdev.konsist.core.declaration.koobjectdeclaration.forkomodifierprovider

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

class KoObjectDeclarationForKoCompanionModifierProviderTest {
    @Test
    fun `object-without-companion-modifier`() {
        // given
        val sut = getSnippetFile("object-without-companion-modifier")
            .objects()
            .first()

        // then
        sut.hasCompanionModifier shouldBeEqualTo false
    }

    @Test
    fun `companion-object`() {
        // given
        val sut = getSnippetFile("companion-object")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasCompanionModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/forkomodifierprovider/snippet/forkocompanionmodifierprovider/", fileName)
}
