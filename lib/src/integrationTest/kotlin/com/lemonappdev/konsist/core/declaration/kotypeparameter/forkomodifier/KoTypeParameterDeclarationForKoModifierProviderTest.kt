package com.lemonappdev.konsist.core.declaration.kotypeparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OUT
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoModifierProviderTest {
    @Test
    fun `class-type-parameter-without-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-without-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OUT) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo false
            hasModifier(listOf(OUT)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OUT)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(OUT) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-parameter-with-modifier`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-modifier")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(OUT)
            numModifiers shouldBeEqualTo 1
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            hasModifier(OUT) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo true
            hasModifier(listOf(OUT)) shouldBeEqualTo true
            hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo true
            hasModifier(setOf(OUT)) shouldBeEqualTo true
            hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo true
            hasAllModifiers(OUT) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo true
            hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo true
            hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-without-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-without-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OUT) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo false
            hasModifier(listOf(OUT)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OUT)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(OUT) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-with-modifier`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-with-modifier")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(OUT)
            numModifiers shouldBeEqualTo 1
            hasModifiers() shouldBeEqualTo true
            hasModifier(emptyList()) shouldBeEqualTo true
            hasModifier(emptySet()) shouldBeEqualTo true
            hasAllModifiers(emptyList()) shouldBeEqualTo true
            hasAllModifiers(emptySet()) shouldBeEqualTo true
            hasModifier(OUT) shouldBeEqualTo true
            hasModifier(PROTECTED) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo true
            hasModifier(listOf(OUT)) shouldBeEqualTo true
            hasModifier(listOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo true
            hasModifier(setOf(OUT)) shouldBeEqualTo true
            hasModifier(setOf(PROTECTED)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo true
            hasAllModifiers(OUT) shouldBeEqualTo true
            hasAllModifiers(PROTECTED) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo true
            hasAllModifiers(listOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo true
            hasAllModifiers(setOf(PROTECTED)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-type-parameter-without-modifier`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-without-modifier")
                .functions()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OUT) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo false
            hasModifier(listOf(OUT)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OUT)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(OUT) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-type-parameter-without-modifier`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-without-modifier")
                .properties()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OUT) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo false
            hasModifier(listOf(OUT)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OUT)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(OUT) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-parameter-without-modifier`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-without-modifier")
                .typeAliases
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifier(emptyList()) shouldBeEqualTo false
            hasModifier(emptySet()) shouldBeEqualTo false
            hasAllModifiers(emptyList()) shouldBeEqualTo false
            hasAllModifiers(emptySet()) shouldBeEqualTo false
            hasModifier(OUT) shouldBeEqualTo false
            hasModifier(OUT, DATA) shouldBeEqualTo false
            hasModifier(listOf(OUT)) shouldBeEqualTo false
            hasModifier(listOf(OUT, DATA)) shouldBeEqualTo false
            hasModifier(setOf(OUT)) shouldBeEqualTo false
            hasModifier(setOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(OUT) shouldBeEqualTo false
            hasAllModifiers(OUT, DATA) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(listOf(OUT, DATA)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT)) shouldBeEqualTo false
            hasAllModifiers(setOf(OUT, DATA)) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/forkomodifier/snippet/forkomodifierprovider/",
            fileName,
        )
}
