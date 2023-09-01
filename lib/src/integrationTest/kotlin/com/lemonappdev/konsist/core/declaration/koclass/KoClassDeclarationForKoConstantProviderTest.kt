package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoConstantProviderTest {
    @Test
    fun `class-has-no-constant`() {
        // given
        val sut = getSnippetFile("class-has-no-constant")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            constants shouldBeEqualTo emptyList()
            numConstants shouldBeEqualTo 0
            hasConstants() shouldBeEqualTo false
            hasConstants("SAMPLE_CONSTANT") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-contains-constant`() {
        // given
        val sut = getSnippetFile("class-contains-constant")
            .classes()
            .first()

        // then
        sut.constants.map { it.name } shouldBeEqualTo listOf("SAMPLE_CONSTANT")
    }

    @Test
    fun `class-has-constants`() {
        // given
        val sut = getSnippetFile("class-has-constants")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            numConstants shouldBeEqualTo 2
            hasConstants() shouldBeEqualTo true
            hasConstants("SAMPLE_CONSTANT_1") shouldBeEqualTo true
            hasConstants("SAMPLE_CONSTANT_1", "SAMPLE_CONSTANT_2") shouldBeEqualTo true
            hasConstants("OTHER_CONSTANT_1") shouldBeEqualTo false
        }
    }
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoconstantprovider/", fileName)
}
