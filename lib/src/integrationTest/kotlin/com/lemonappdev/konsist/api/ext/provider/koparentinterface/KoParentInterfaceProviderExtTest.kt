package com.lemonappdev.konsist.api.ext.provider.koparentinterface

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentInterfaceOf
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentInterfaceProviderExtTest {
    @Test
    fun `class-is-inheriting-from-a-parent-class-imported-from-external-file`() {
        // given
        val sut = getSnippetFile("class-is-inheriting-from-a-parent-class-imported-from-external-file")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
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
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
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
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
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
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-is-inheriting-from-a-parent-class-imported-from-external-file`() {
        // given
        val sut = getSnippetFile("interface-is-inheriting-from-a-parent-class-imported-from-external-file")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-is-inheriting-from-a-parent-class-defined-in-the-same-file`() {
        // given
        val sut = getSnippetFile("interface-is-inheriting-from-a-parent-class-defined-in-the-same-file")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasParentInterfaceOf<SampleParentInterface>() shouldBeEqualTo true
            hasParentInterfaceOf<SampleParentInterface1>() shouldBeEqualTo false
            hasParentInterfaceOf<SampleParentClass>() shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("api/ext/provider/koparentinterface/snippet/", fileName)
}
