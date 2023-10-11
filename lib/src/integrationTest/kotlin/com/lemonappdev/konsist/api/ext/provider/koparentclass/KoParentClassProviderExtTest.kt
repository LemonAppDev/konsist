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
    fun `class-has-parent-class-with-imports`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-with-imports")
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
    fun `class-has-parent-class-without-imports`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-without-imports")
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
    fun `object-has-parent-class-with-imports`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-with-imports")
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
    fun `object-has-parent-class-without-imports`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-without-imports")
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
