package com.lemonappdev.konsist.api.ext.provider.koexternalparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasExternalParentOf
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalParentProviderExtTest {
    @Test
    fun `class-has-external-parent`() {
        // given
        val sut = getSnippetFile("class-has-external-parent")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasExternalParentOf<SampleParentInterface1>() shouldBeEqualTo false
            hasExternalParentOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-external-parent`() {
        // given
        val sut = getSnippetFile("object-has-external-parent")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasExternalParentOf<SampleParentInterface1>() shouldBeEqualTo false
            hasExternalParentOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-external-parent`() {
        // given
        val sut = getSnippetFile("interface-has-external-parent")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasExternalParentOf<SampleParentInterface>() shouldBeEqualTo true
            hasExternalParentOf<SampleParentInterface1>() shouldBeEqualTo false
            hasExternalParentOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koexternalparent/snippet/", fileName)
}
