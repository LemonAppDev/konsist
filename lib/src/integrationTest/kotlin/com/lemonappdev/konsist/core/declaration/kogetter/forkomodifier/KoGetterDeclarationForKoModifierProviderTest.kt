package com.lemonappdev.konsist.core.declaration.kogetter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoModifierProviderTest {
    @Test
    fun `getter-has-no-modifiers`() {
        // given
        val sut =
            getSnippetFile("getter-has-no-modifiers")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(emptyList()) shouldBeEqualTo false
            it?.hasModifier(emptySet()) shouldBeEqualTo false
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo false
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo false
            it?.hasModifier(KoModifier.OPEN) shouldBeEqualTo false
            it?.hasModifier(KoModifier.OPEN, DATA) shouldBeEqualTo false
            it?.hasModifier(listOf(KoModifier.OPEN)) shouldBeEqualTo false
            it?.hasModifier(listOf(KoModifier.OPEN, DATA)) shouldBeEqualTo false
            it?.hasModifier(setOf(KoModifier.OPEN)) shouldBeEqualTo false
            it?.hasModifier(setOf(KoModifier.OPEN, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(KoModifier.OPEN) shouldBeEqualTo false
            it?.hasAllModifiers(KoModifier.OPEN, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(KoModifier.OPEN)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(KoModifier.OPEN, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(KoModifier.OPEN)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(KoModifier.OPEN, DATA)) shouldBeEqualTo false
            it?.hasModifiers(KoModifier.OPEN) shouldBeEqualTo false
            it?.hasModifiers(KoModifier.OPEN, DATA) shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("getter-has-private-modifier")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo listOf(PRIVATE)
            it?.numModifiers shouldBeEqualTo 1
            it?.hasModifiers() shouldBeEqualTo true
            it?.hasModifier(emptyList()) shouldBeEqualTo true
            it?.hasModifier(emptySet()) shouldBeEqualTo true
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo true
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo true
            it?.hasModifier(PRIVATE) shouldBeEqualTo true
            it?.hasModifier(PROTECTED) shouldBeEqualTo false
            it?.hasModifier(PRIVATE, DATA) shouldBeEqualTo true
            it?.hasModifier(listOf(PRIVATE)) shouldBeEqualTo true
            it?.hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(listOf(PRIVATE, DATA)) shouldBeEqualTo true
            it?.hasModifier(setOf(PRIVATE)) shouldBeEqualTo true
            it?.hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(setOf(PRIVATE, DATA)) shouldBeEqualTo true
            it?.hasAllModifiers(PRIVATE) shouldBeEqualTo true
            it?.hasAllModifiers(PROTECTED) shouldBeEqualTo false
            it?.hasAllModifiers(PRIVATE, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(PRIVATE)) shouldBeEqualTo true
            it?.hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(PRIVATE, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(PRIVATE)) shouldBeEqualTo true
            it?.hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(PRIVATE, DATA)) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kogetter/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )
}
