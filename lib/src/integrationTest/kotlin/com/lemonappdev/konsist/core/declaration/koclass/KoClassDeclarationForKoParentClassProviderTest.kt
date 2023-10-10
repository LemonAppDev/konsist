package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoParentClassProviderTest {
    @Test
    fun `class-has-no-parent-class`() {
        // given
        val sut = getSnippetFile("class-has-no-parent-class")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentClass shouldBeEqualTo null
            hasParentClass() shouldBeEqualTo false
            hasParentClass { it.name == "SampleParentClass"} shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-only-parent-class`() {
        // given
        val sut = getSnippetFile("class-has-only-parent-class")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass"} shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass"} shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
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
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass"} shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass"} shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoparentclassprovider/", fileName)
}
