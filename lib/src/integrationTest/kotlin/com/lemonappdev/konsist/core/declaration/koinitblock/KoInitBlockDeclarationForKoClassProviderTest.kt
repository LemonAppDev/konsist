package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoClassProviderTest {
    @Test
    fun `init-block-contains-no-classes`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        sut.classes(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass", "SampleLocalClass")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass")

        sut.classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `count-classes`() {
        // given
        val sut = getSnippetFile("count-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numClasses(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            numClasses(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            numClasses(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            countClasses { it.hasOpenModifier } shouldBeEqualTo 1
            countClasses(includeNested = true, includeLocal = true) { it.hasOpenModifier } shouldBeEqualTo 2
            countClasses { it.name == "SampleClass" && it.hasInternalModifier } shouldBeEqualTo 0
        }
    }

    @Test
    fun `contains-classes-with-specified-conditions`() {
        // given
        val sut = getSnippetFile("contains-classes-with-specified-conditions")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            containsClass { it.name == "SampleClass" && it.hasOpenModifier } shouldBeEqualTo true
            containsClass { it.name == "SampleClass" && it.hasPublicModifier } shouldBeEqualTo false
            containsClass { it.name == "SampleClass" && it.hasModifiers(PRIVATE, OPEN) } shouldBeEqualTo false
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
            ) { it.name == "SampleNestedClass" && it.hasOpenModifier } shouldBeEqualTo true
            containsClass(
                includeNested = false,
                includeLocal = false,
            ) { it.name == "SampleNestedClass" && it.hasOpenModifier } shouldBeEqualTo false
            containsClass(
                includeNested = true,
                includeLocal = false,
            ) { it.name == "SampleNestedClass" && it.hasAbstractModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `contains-classes-with-specified-regex`() {
        // given
        val regex1 = Regex("[a-zA-Z]+")
        val regex2 = Regex("[0-9]+")
        val sut = getSnippetFile("contains-classes-with-specified-regex")
            .classes()
            .first()
            .initBlocks
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkoclassprovider/", fileName)
}
