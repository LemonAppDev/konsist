package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoUpperBoundsProviderTest {
    @Test
    fun `function-type-parameter-without-upper-bound`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-without-upper-bound")
                .functions()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds shouldBeEqualTo emptyList()
            numUpperBounds shouldBeEqualTo 0
            countUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasUpperBounds() shouldBeEqualTo false
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo false
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo false
            hasUpperBoundWithName("sampleUpperBound") shouldBeEqualTo false
            hasUpperBoundWithName(listOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames("sampleUpperBound1", "sampleUpperBound2") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-type-parameter-with-one-upper-bound`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-with-one-upper-bound")
                .functions()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 1
            numUpperBounds shouldBeEqualTo 1
            countUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { it.hasNameStartingWith("Int") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo true
        }
    }

    @Test
    fun `function-type-parameter-with-two-upper-bounds`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-with-two-upper-bounds")
                .functions()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 2
            numUpperBounds shouldBeEqualTo 2
            countUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo 2
            countUpperBounds { upperBound -> upperBound.isKotlinType } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "CharSequence") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { upperBound -> upperBound.isKotlinType } shouldBeEqualTo true
            hasAllUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo true
            hasAllUpperBounds { upperBound -> upperBound.isInterface } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-parameter-without-upper-bound`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-without-upper-bound")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds shouldBeEqualTo emptyList()
            numUpperBounds shouldBeEqualTo 0
            countUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasUpperBounds() shouldBeEqualTo false
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo false
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo false
            hasUpperBoundWithName("sampleUpperBound") shouldBeEqualTo false
            hasUpperBoundWithName(listOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames("sampleUpperBound1", "sampleUpperBound2") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-with-one-upper-bound`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-one-upper-bound")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 1
            numUpperBounds shouldBeEqualTo 1
            countUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { it.hasNameStartingWith("Int") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-with-two-upper-bounds`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-two-upper-bounds")
                .classes()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 2
            numUpperBounds shouldBeEqualTo 2
            countUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo 2
            countUpperBounds { upperBound -> upperBound.isKotlinType } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "CharSequence") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { upperBound -> upperBound.isKotlinType } shouldBeEqualTo true
            hasAllUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo true
            hasAllUpperBounds { upperBound -> upperBound.isInterface } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-without-upper-bound`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-without-upper-bound")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds shouldBeEqualTo emptyList()
            numUpperBounds shouldBeEqualTo 0
            countUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasUpperBounds() shouldBeEqualTo false
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo false
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo false
            hasUpperBoundWithName("sampleUpperBound") shouldBeEqualTo false
            hasUpperBoundWithName(listOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames("sampleUpperBound1", "sampleUpperBound2") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-type-parameter-with-one-upper-bound`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-with-one-upper-bound")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 1
            numUpperBounds shouldBeEqualTo 1
            countUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { it.hasNameStartingWith("Int") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-type-parameter-with-two-upper-bounds`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-with-two-upper-bounds")
                .interfaces()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 2
            numUpperBounds shouldBeEqualTo 2
            countUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo 2
            countUpperBounds { upperBound -> upperBound.isKotlinType } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "CharSequence") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { upperBound -> upperBound.isKotlinType } shouldBeEqualTo true
            hasAllUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo true
            hasAllUpperBounds { upperBound -> upperBound.isInterface } shouldBeEqualTo false
        }
    }

    @Test
    fun `property-type-parameter-without-upper-bound`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-without-upper-bound")
                .properties()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds shouldBeEqualTo emptyList()
            numUpperBounds shouldBeEqualTo 0
            countUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasUpperBounds() shouldBeEqualTo false
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo false
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo false
            hasUpperBoundWithName("sampleUpperBound") shouldBeEqualTo false
            hasUpperBoundWithName(listOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames("sampleUpperBound1", "sampleUpperBound2") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-type-parameter-with-one-upper-bound`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-with-one-upper-bound")
                .properties()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 1
            numUpperBounds shouldBeEqualTo 1
            countUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { it.hasNameStartingWith("Int") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("List") } shouldBeEqualTo true
        }
    }

    @Test
    fun `property-type-parameter-with-two-upper-bounds`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-with-two-upper-bounds")
                .properties()
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds.size shouldBeEqualTo 2
            numUpperBounds shouldBeEqualTo 2
            countUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo 2
            countUpperBounds { upperBound -> upperBound.isKotlinType } shouldBeEqualTo 1
            hasUpperBounds() shouldBeEqualTo true
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo true
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo true
            hasUpperBoundWithName("List<*>") shouldBeEqualTo true
            hasUpperBoundWithName("Int") shouldBeEqualTo false
            hasUpperBoundWithName("List<*>", "Int") shouldBeEqualTo true
            hasUpperBoundWithName(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(listOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(listOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundWithName(setOf("Int")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("List<*>", "Int")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "CharSequence") shouldBeEqualTo true
            hasUpperBoundsWithAllNames("List<*>", "Int") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(listOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("List<*>")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "CharSequence")) shouldBeEqualTo true
            hasUpperBoundsWithAllNames(setOf("List<*>", "Int")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("List") } shouldBeEqualTo true
            hasUpperBound { upperBound -> upperBound.isKotlinType } shouldBeEqualTo true
            hasAllUpperBounds { it.hasNameStartingWith("List") || it.hasNameStartingWith("Char") } shouldBeEqualTo true
            hasAllUpperBounds { upperBound -> upperBound.isInterface } shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-parameter-without-upper-bound`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-without-upper-bound")
                .typeAliases
                .first()
                .typeParameters
                .first()

        // then
        assertSoftly(sut) {
            upperBounds shouldBeEqualTo emptyList()
            numUpperBounds shouldBeEqualTo 0
            countUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo 0
            hasUpperBounds() shouldBeEqualTo false
            hasUpperBoundWithName(emptyList()) shouldBeEqualTo false
            hasUpperBoundWithName(emptySet()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptyList()) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(emptySet()) shouldBeEqualTo false
            hasUpperBoundWithName("sampleUpperBound") shouldBeEqualTo false
            hasUpperBoundWithName(listOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundWithName(setOf("sampleUpperBound")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames("sampleUpperBound1", "sampleUpperBound2") shouldBeEqualTo false
            hasUpperBoundsWithAllNames(listOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBoundsWithAllNames(setOf("sampleUpperBound1", "sampleUpperBound2")) shouldBeEqualTo false
            hasUpperBound { it.hasNameStartingWith("other") } shouldBeEqualTo false
            hasAllUpperBounds { it.hasNameStartingWith("sample") } shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/snippet/forkoupperboundsprovider/",
            fileName,
        )
}
