package com.lemonappdev.konsist.api.ext.provider.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentOf
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalGenericInterface
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentProviderExtTest {
    @Test
    fun `class-has-each-type-of-parents`() {
        // given
        val sut = getSnippetFile("class-has-each-type-of-parents")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleExternalInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
            hasParentOf<SampleInterface>() shouldBeEqualTo false
            hasParentOf<SampleExternalClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-each-type-of-parents`() {
        // given
        val sut = getSnippetFile("object-has-each-type-of-parents")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleExternalInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
            hasParentOf<SampleInterface>() shouldBeEqualTo false
            hasParentOf<SampleExternalClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-each-type-of-parents`() {
        // given
        val sut = getSnippetFile("interface-has-each-type-of-parents")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
//            hasParentOf<SampleInterfaceFromFile>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleExternalInterface>() shouldBeEqualTo true
            hasParentOf<SampleInterface>() shouldBeEqualTo false
            hasParentOf<SampleExternalGenericInterface<Int>>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparent/snippet/", fileName)
}
