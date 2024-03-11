package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.INTERNAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoClassProviderTest {
    @Test
    fun `interface-has-no-classes`() {
        // given
        val sut =
            getSnippetFile("interface-has-no-classes")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            classes() shouldBeEqualTo emptyList()
            hasClasses() shouldBeEqualTo false
            hasClassWithName("SampleClass") shouldBeEqualTo false
            hasClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo false
            hasClass { it.name == "SampleClass" } shouldBeEqualTo false
            hasAllClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-two-classes`() {
        // given
        val sut =
            getSnippetFile("interface-has-two-classes")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            hasClasses() shouldBeEqualTo true
            hasClassWithName("SampleClass1") shouldBeEqualTo true
            hasClassWithName("SampleClass1", "OtherClass") shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1") shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo true
            hasClassesWithAllNames("SampleClass1", "OtherClass") shouldBeEqualTo false
            hasClass { it.name == "SampleClass1" } shouldBeEqualTo true
            hasClass { it.hasNameEndingWith("Class1") } shouldBeEqualTo true
            hasAllClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllClasses { it.hasNameEndingWith("Class1") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleNestedClass", "SampleLocalClass")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleNestedClass")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes")
                .interfaces()
                .first()

        // then
        val expected = listOf("SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `interface-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut =
            getSnippetFile("interface-contains-nested-and-local-classes")
                .interfaces()
                .first()

        // then
        val expected = emptyList<String>()

        sut.classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes`() {
        // given
        val sut =
            getSnippetFile("count-classes")
                .interfaces()
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

    @Test
    fun `contains-classes-with-specified-conditions`() {
        // given
        val sut =
            getSnippetFile("contains-classes-with-specified-conditions")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            containsClass { it.name == "SampleClass" && it.hasPrivateModifier } shouldBeEqualTo true
            containsClass { it.name == "SampleClass" && it.hasModifiers(PRIVATE, OPEN) } shouldBeEqualTo true
            containsClass { it.name == "SampleClass" && it.hasPublicModifier } shouldBeEqualTo false
            containsClass { it.name == "SampleClass" && it.hasModifiers(INTERNAL, PRIVATE) } shouldBeEqualTo false
            containsClass(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "SampleLocalClass" } shouldBeEqualTo true
            containsClass(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "SampleLocalClass" } shouldBeEqualTo false
            containsClass(
                includeNested = false,
                includeLocal = true,
            ) { it.name == "SampleOtherClass" } shouldBeEqualTo false
            containsClass(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "SampleNestedClass" && it.hasPrivateModifier } shouldBeEqualTo true
            containsClass(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "SampleNestedClass" && it.hasPrivateModifier } shouldBeEqualTo false
            containsClass(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "SampleNestedClass" && it.hasOpenModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-classes-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut =
            getSnippetFile("contains-classes-with-specified-regex")
                .interfaces()
                .first()

        // then
        assertSoftly(sut) {
            containsClass(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsClass(
                includeNested = false,
                includeLocal = true,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsClass(
                includeNested = true,
                includeLocal = false,
            ) { it.name.matches(regex1) } shouldBeEqualTo true
            containsClass(
                includeNested = false,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
            containsClass(
                includeNested = false,
                includeLocal = true,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
            containsClass(
                includeNested = true,
                includeLocal = false,
            ) { it.name.matches(regex2) } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkoclassprovider/", fileName)
}
