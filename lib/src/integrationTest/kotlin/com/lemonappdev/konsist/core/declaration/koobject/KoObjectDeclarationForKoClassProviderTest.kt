package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoClassProviderTest {
    @Test
    fun `object-has-no-classes`() {
        // given
        val sut =
            getSnippetFile("object-has-no-classes")
                .objects()
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
    fun `object-has-two-classes`() {
        // given
        val sut =
            getSnippetFile("object-has-two-classes")
                .objects()
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
    fun `object-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("SampleNestedClass", "SampleLocalClass")

        sut
            .classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("SampleNestedClass")

        sut
            .classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut
            .classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("object-contains-nested-and-local-classes")
                .objects()
                .first()

        // then
        val expected = emptyList<String>()

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
                .objects()
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

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkoclassprovider/", fileName)
}
