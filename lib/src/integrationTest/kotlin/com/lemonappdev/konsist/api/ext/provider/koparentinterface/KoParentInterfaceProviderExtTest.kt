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
    fun `class-has-parent-interface-with-imports`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-with-imports")
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
    fun `class-has-parent-interface-without-imports`() {
        // given
        val sut = getSnippetFile("class-has-parent-interface-without-imports")
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
    fun `object-has-parent-interface-with-imports`() {
        // given
        val sut = getSnippetFile("object-has-parent-interface-with-imports")
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
    fun `object-has-parent-interface-without-imports`() {
        // given
        val sut = getSnippetFile("object-has-parent-interface-without-imports")
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
    fun `interface-has-parent-interface-with-imports`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interface-with-imports")
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
    fun `interface-has-parent-interface-without-imports`() {
        // given
        val sut = getSnippetFile("interface-has-parent-interface-without-imports")
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
