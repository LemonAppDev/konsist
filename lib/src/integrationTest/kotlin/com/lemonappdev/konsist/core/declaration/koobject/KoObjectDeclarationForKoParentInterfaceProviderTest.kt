package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `object-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("object-has-no-parent-interface")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces shouldBeEqualTo emptyList()
            numParentInterfaces shouldBeEqualTo 0
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaces("SampleInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-parent-interfaces`() {
        // given
        val sut = getSnippetFile("object-has-only-parent-interfaces")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParentInterfaces shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-and-interfaces`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-and-interfaces")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name } shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentinterfaceprovider/", fileName)
}
