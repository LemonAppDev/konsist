package com.lemonappdev.konsist.core.declaration.kosetter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoModifierProviderTest {
    @Test
    fun `setter-has-no-modifiers`() {
        // given
        val sut =
            getSnippetFile("setter-has-no-modifiers")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(PRIVATE) shouldBeEqualTo false
            it?.hasAllModifiers(PRIVATE) shouldBeEqualTo false
            it?.hasAllModifiers(PRIVATE, DATA) shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("setter-has-private-modifier")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.modifiers shouldNotBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 1
            it?.hasModifiers() shouldBeEqualTo true
            it?.hasModifier(PRIVATE) shouldBeEqualTo true
            it?.hasModifier(DATA) shouldBeEqualTo false
            it?.hasModifier(PRIVATE, DATA) shouldBeEqualTo true
            it?.hasAllModifiers(PRIVATE) shouldBeEqualTo true
            it?.hasAllModifiers(DATA) shouldBeEqualTo false
            it?.hasAllModifiers(PRIVATE, DATA) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kosetter/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )
}
