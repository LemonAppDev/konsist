package com.lemonappdev.konsist.api.ext.provider.koparentinterface

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.provider.hasParentInterfaceOf
import com.lemonappdev.konsist.externalsample.SampleExternalClass
import com.lemonappdev.konsist.externalsample.SampleExternalInterface
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentInterface
import com.lemonappdev.konsist.testdata.SampleParentInterface1
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentInterfaceProviderExtTest {
    @Test
    fun `class-has-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-from-import")
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
    fun `class-has-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-from-file")
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
    fun `object-has-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("object-has-parent-interface-from-import")
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
    fun `object-has-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("object-has-parent-interface-from-file")
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
    fun `interface-has-parent-interface-from-import`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interface-from-import")
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
    fun `interface-has-parent-interface-from-file`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interface-from-file")
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
