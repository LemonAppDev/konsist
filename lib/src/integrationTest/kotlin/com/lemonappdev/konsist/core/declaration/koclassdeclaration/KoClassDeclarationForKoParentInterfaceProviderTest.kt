package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoParentInterfaceProviderTest {
    @Test
    fun `class-has-no-parent-interface`() {
        // given
        val sut = getSnippetFile("class-has-no-parent-interface")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.toList() shouldBeEqualTo emptyList()
            numParentInterfaces shouldBeEqualTo 0
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaces("SampleInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-only-parent-interfaces`() {
        // given
        val sut = getSnippetFile("class-has-only-parent-interfaces")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name }.toList() shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            numParentInterfaces shouldBeEqualTo 2
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-class-and-interfaces`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-and-interfaces")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentInterfaces.map { it.name }.toList() shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkoparentinterfaceprovider/", fileName)
}
