package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoModifierProviderTest {
    @Test
    fun `parameter-has-no-modifiers`() {
        // given
        val sut =
            getSnippetFile("parameter-has-no-modifiers")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(OPEN) shouldBeEqualTo false
            it?.hasModifier(OPEN, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(OPEN) shouldBeEqualTo false
            it?.hasAllModifiers(OPEN, DATA) shouldBeEqualTo false
            it?.hasModifiers(OPEN) shouldBeEqualTo false
            it?.hasModifiers(OPEN, DATA) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("parameter-has-public-modifier")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldNotBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 1
            it?.hasModifiers() shouldBeEqualTo true
            it?.hasModifier(PUBLIC) shouldBeEqualTo true
            it?.hasModifier(DATA) shouldBeEqualTo false
            it?.hasModifier(PUBLIC, DATA) shouldBeEqualTo true
            it?.hasAllModifiers(PUBLIC) shouldBeEqualTo true
            it?.hasAllModifiers(DATA) shouldBeEqualTo false
            it?.hasAllModifiers(PUBLIC, DATA) shouldBeEqualTo false
            it?.hasModifiers(PUBLIC) shouldBeEqualTo true
            it?.hasModifiers(PRIVATE) shouldBeEqualTo false
            it?.hasModifiers(PUBLIC, PRIVATE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameter/forkomodifier/snippet/forkomodifierprovider/", fileName)
}
