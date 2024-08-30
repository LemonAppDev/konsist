package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoClassProviderTest {
    @Test
    fun `class-has-no-classes`() {
        // given
        val sut =
            getSnippetFile("class-has-no-classes")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            classes() shouldBeEqualTo emptyList()
            hasClasses() shouldBeEqualTo false
            hasClassWithName(emptyList()) shouldBeEqualTo false
            hasClassWithName(emptySet()) shouldBeEqualTo false
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassWithName("SampleClass") shouldBeEqualTo false
            hasClassWithName(listOf("SampleClass")) shouldBeEqualTo false
            hasClassWithName(setOf("SampleClass")) shouldBeEqualTo false
            hasClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("SampleClass1", "SampleClass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("SampleClass1", "SampleClass2")) shouldBeEqualTo false
            hasClass { it.name == "SampleClass" } shouldBeEqualTo false
            hasAllClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-two-classes`() {
        // given
        val sut =
            getSnippetFile("class-has-two-classes")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasClasses() shouldBeEqualTo true
            hasClassWithName(emptyList()) shouldBeEqualTo true
            hasClassWithName(emptySet()) shouldBeEqualTo true
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassWithName("SampleClass1") shouldBeEqualTo true
            hasClassWithName("SampleClass1", "OtherClass") shouldBeEqualTo true
            hasClassWithName(listOf("SampleClass1")) shouldBeEqualTo true
            hasClassWithName(listOf("SampleClass1", "OtherClass")) shouldBeEqualTo true
            hasClassWithName(setOf("SampleClass1")) shouldBeEqualTo true
            hasClassWithName(setOf("SampleClass1", "OtherClass")) shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1") shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1", "OtherClass") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("SampleClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("SampleClass1", "SampleClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("SampleClass1", "OtherClass")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("SampleClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("SampleClass1", "SampleClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("SampleClass1", "OtherClass")) shouldBeEqualTo false
            hasClass { it.name == "SampleClass1" } shouldBeEqualTo true
            hasClass { it.hasNameEndingWith("Class1") } shouldBeEqualTo true
            hasAllClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllClasses { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-classes")
                .classes()
                .first()

        // then
        val expected = listOf("SampleLocalClass", "SampleClassNestedInsideObject")

        sut
            .classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-classes")
                .classes()
                .first()

        // then
        val expected = listOf("SampleClassNestedInsideObject")

        sut
            .classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-classes")
                .classes()
                .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut
            .classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("class-contains-nested-and-local-classes")
                .classes()
                .first()

        // then
        val expected = emptyList<KoClassDeclaration>()

        sut
            .classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes`() {
        // given
        val sut =
            getSnippetFile("count-classes")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            numClasses(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numClasses(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClasses(includeNested = false, includeLocal = false) { it.hasPrivateModifier } shouldBeEqualTo 1
            countClasses { it.hasPrivateModifier } shouldBeEqualTo 2
            countClasses { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoclassprovider/", fileName)
}
