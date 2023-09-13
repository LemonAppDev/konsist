package com.lemonappdev.konsist.api.ext.provider.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentOf
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentProviderExtTest {
    @Test
    fun `class-has-two-parents-with-imports`() {
        // given
        val sut = getSnippetFile("class-has-two-parents-with-imports")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-parents-without-imports`() {
        // given
        val sut = getSnippetFile("class-has-two-parents-without-imports")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-two-parents-with-imports`() {
        // given
        val sut = getSnippetFile("object-has-two-parents-with-imports")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-two-parents-without-imports`() {
        // given
        val sut = getSnippetFile("object-has-two-parents-without-imports")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentClass>() shouldBeEqualTo true
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-parent-with-import`() {
        // given
        val sut = getSnippetFile("interface-has-parent-with-import")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleInterface>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-parent-without-import`() {
        // given
        val sut = getSnippetFile("interface-has-parent-without-import")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentOf<SampleInterface>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparent/snippet/", fileName)
}
