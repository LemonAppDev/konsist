package com.lemonappdev.konsist.core.declaration.kotypeargument.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.IN
import com.lemonappdev.konsist.api.KoModifier.OUT
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoModifierProviderTest {
    @Test
    fun `not-generic-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(emptyList()) shouldBeEqualTo false
            it?.hasModifier(emptySet()) shouldBeEqualTo false
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo false
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo false
            it?.hasModifier(OUT) shouldBeEqualTo false
            it?.hasModifier(OUT, DATA) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(OUT) shouldBeEqualTo false
            it?.hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(emptyList()) shouldBeEqualTo false
            it?.hasModifier(emptySet()) shouldBeEqualTo false
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo false
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo false
            it?.hasModifier(OUT) shouldBeEqualTo false
            it?.hasModifier(OUT, DATA) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(OUT) shouldBeEqualTo false
            it?.hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(emptyList()) shouldBeEqualTo false
            it?.hasModifier(emptySet()) shouldBeEqualTo false
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo false
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo false
            it?.hasModifier(OUT) shouldBeEqualTo false
            it?.hasModifier(OUT, DATA) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(OUT) shouldBeEqualTo false
            it?.hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifier(emptyList()) shouldBeEqualTo false
            it?.hasModifier(emptySet()) shouldBeEqualTo false
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo false
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo false
            it?.hasModifier(OUT) shouldBeEqualTo false
            it?.hasModifier(OUT, DATA) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(OUT) shouldBeEqualTo false
            it?.hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo listOf(OUT)
            it?.numModifiers shouldBeEqualTo 1
            it?.hasModifiers() shouldBeEqualTo true
            it?.hasModifier(emptyList()) shouldBeEqualTo true
            it?.hasModifier(emptySet()) shouldBeEqualTo true
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo true
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo true
            it?.hasModifier(OUT) shouldBeEqualTo true
            it?.hasModifier(PROTECTED) shouldBeEqualTo false
            it?.hasModifier(OUT, DATA) shouldBeEqualTo true
            it?.hasModifier(listOf(OUT)) shouldBeEqualTo true
            it?.hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(listOf(OUT, DATA)) shouldBeEqualTo true
            it?.hasModifier(setOf(OUT)) shouldBeEqualTo true
            it?.hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(setOf(OUT, DATA)) shouldBeEqualTo true
            it?.hasAllModifiers(OUT) shouldBeEqualTo true
            it?.hasAllModifiers(PROTECTED) shouldBeEqualTo false
            it?.hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT)) shouldBeEqualTo true
            it?.hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT)) shouldBeEqualTo true
            it?.hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-type-projection`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-type-projection")
                .properties()
                .first()
                .type
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.modifiers shouldBeEqualTo listOf(IN)
            it?.numModifiers shouldBeEqualTo 1
            it?.hasModifiers() shouldBeEqualTo true
            it?.hasModifier(emptyList()) shouldBeEqualTo true
            it?.hasModifier(emptySet()) shouldBeEqualTo true
            it?.hasAllModifiers(emptyList()) shouldBeEqualTo true
            it?.hasAllModifiers(emptySet()) shouldBeEqualTo true
            it?.hasModifier(IN) shouldBeEqualTo true
            it?.hasModifier(PROTECTED) shouldBeEqualTo false
            it?.hasModifier(IN, DATA) shouldBeEqualTo true
            it?.hasModifier(listOf(IN)) shouldBeEqualTo true
            it?.hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(listOf(IN, DATA)) shouldBeEqualTo true
            it?.hasModifier(setOf(IN)) shouldBeEqualTo true
            it?.hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasModifier(setOf(IN, DATA)) shouldBeEqualTo true
            it?.hasAllModifiers(IN) shouldBeEqualTo true
            it?.hasAllModifiers(PROTECTED) shouldBeEqualTo false
            it?.hasAllModifiers(IN, DATA) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(IN)) shouldBeEqualTo true
            it?.hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(listOf(IN, DATA)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(IN)) shouldBeEqualTo true
            it?.hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            it?.hasAllModifiers(setOf(IN, DATA)) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )
}
