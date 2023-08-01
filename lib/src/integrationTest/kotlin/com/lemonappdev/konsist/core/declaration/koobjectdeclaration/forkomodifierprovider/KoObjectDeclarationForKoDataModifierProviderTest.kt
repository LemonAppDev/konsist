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

class KoObjectDeclarationForKoDataModifierProviderTest {
    @Test
    fun `object-without-data-modifier`() {
        // given
        val sut = getSnippetFile("object-without-data-modifier")
            .objects()
            .first()

        // then
        sut.hasDataModifier shouldBeEqualTo false
    }

    @Test
    fun `data-object`() {
        // given
        val sut = getSnippetFile("data-object")
            .objects()
            .first()

        // then
        sut.hasDataModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/forkomodifierprovider/snippet/forkodatamodifierprovider/", fileName)
}
