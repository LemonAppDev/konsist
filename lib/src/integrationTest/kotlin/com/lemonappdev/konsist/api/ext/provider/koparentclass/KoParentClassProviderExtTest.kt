package com.lemonappdev.konsist.api.ext.provider.koparentclass

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentClassOf
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentClassProviderExtTest {
    @Test
    fun `class-has-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-from-import")
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
    fun `class-has-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-from-file")
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
    fun `object-has-parent-class-from-import`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-from-import")
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
    fun `object-has-parent-class-from-file`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-from-file")
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
