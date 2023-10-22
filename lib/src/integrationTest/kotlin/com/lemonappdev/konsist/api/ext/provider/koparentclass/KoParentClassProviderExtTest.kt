package com.lemonappdev.konsist.api.ext.provider.koparentclass

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentClassOf
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentClassProviderExtTest {
    @Test
    fun `class-is-inheriting-from-a-parent-class-imported-from-external-file`() {
        // given
        val sut = getSnippetFile("class-is-inheriting-from-a-parent-class-imported-from-external-file")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentClassOf<SampleParentClass>() shouldBeEqualTo true
            hasParentClassOf<SampleParentInterface>() shouldBeEqualTo false
            hasParentClassOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-is-inheriting-from-a-parent-class-defined-in-the-same-file`() {
        // given
        val sut = getSnippetFile("class-is-inheriting-from-a-parent-class-defined-in-the-same-file")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentClassOf<SampleParentClass>() shouldBeEqualTo true
            hasParentClassOf<SampleParentInterface>() shouldBeEqualTo false
            hasParentClassOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-is-inheriting-from-a-parent-class-defined-in-the-same-file`() {
        // given
        val sut = getSnippetFile("object-is-inheriting-from-a-parent-class-defined-in-the-same-file")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasParentClassOf<SampleParentClass>() shouldBeEqualTo true
            hasParentClassOf<SampleParentInterface>() shouldBeEqualTo false
            hasParentClassOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-is-inheriting-from-a-parent-class-imported-from-external-file`() {
        // given
        val sut = getSnippetFile("object-is-inheriting-from-a-parent-class-imported-from-external-file")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasParentClassOf<SampleParentClass>() shouldBeEqualTo true
            hasParentClassOf<SampleParentInterface>() shouldBeEqualTo false
            hasParentClassOf<SampleClass>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparentclass/snippet/", fileName)
}
