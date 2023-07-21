package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class KoClassDeclarationForKoObjectProviderTest {
    @Test
    fun `class-contains-no-objects`() {
        // given
        val sut = getSnippetFile("class-contains-no-objects")
            .classes()
            .first()

        // then
        sut.objects(includeNested = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `class-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("class-contains-objects")
            .classes()
            .first()

        // then
        val expected = listOf("SampleObject", "SampleNestedObject")

        sut.objects(includeNested = true)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("class-contains-objects")
            .classes()
            .first()

        // then
        val expected = listOf("SampleObject")

        sut.objects(includeNested = false)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-objects`() {
        // given
        val sut = getSnippetFile("contains-objects")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            numObjects(includeNested = false) shouldBeEqualTo 1
            numObjects(includeNested = true) shouldBeEqualTo 2
            containsObject("SampleObject", includeNested = false) shouldBeEqualTo true
            containsObject("SampleNestedObject", includeNested = false) shouldBeEqualTo false
            containsObject("SampleNestedObject", includeNested = true) shouldBeEqualTo true
            containsObject("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoobjectprovider/", fileName)
}
