package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentClassProviderTest {
    @Test
    fun `object-has-no-parent-class`() {
        // given
        val sut = getSnippetFile("object-has-no-parent-class")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass shouldBeEqualTo null
            hasParentClass() shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-parent-class`() {
        // given
        val sut = getSnippetFile("object-has-only-parent-class")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
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
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentclassprovider/", fileName)
}
