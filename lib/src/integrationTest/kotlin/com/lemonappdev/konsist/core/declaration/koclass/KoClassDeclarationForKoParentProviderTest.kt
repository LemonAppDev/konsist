package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoParentProviderTest {
    @Test
    fun `class-has-no-parents`() {
        // given
        val sut = getSnippetFile("class-has-no-parents")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parents shouldBeEqualTo emptyList()
            numParents shouldBeEqualTo 0
            hasParents() shouldBeEqualTo false
            hasParents("SampleClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-class-and-interfaces`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-and-interfaces")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo listOf(
                "SampleParentClass",
                "SampleParentInterface1",
                "SampleParentInterface2",
            )
            numParents shouldBeEqualTo 3
            hasParents() shouldBeEqualTo true
            hasParents("SampleParentClass") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParents("SampleParentClass", "SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-java-parents`() {
        // given
        val sut = getSnippetFile("class-has-java-parents")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parents.map { it.name } shouldBeEqualTo listOf(
                "SampleJavaParentClass",
                "SampleJavaParentInterface",
            )
            numParents shouldBeEqualTo 2
            hasParents() shouldBeEqualTo true
            hasParents("SampleJavaParentClass") shouldBeEqualTo true
            hasParents("OtherInterface") shouldBeEqualTo false
            hasParents("SampleJavaParentClass", "SampleJavaParentInterface") shouldBeEqualTo true
            hasParents("SampleJavaParentClass", "SampleJavaParentInterface", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoparentprovider/", fileName)
}
