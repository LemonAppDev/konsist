package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoNameProviderTest {
    @Test
    fun `class-with-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        sut?.name shouldBeEqualTo "SampleSuperClass"
    }

    @Test
    fun `class-with-generic-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        sut?.name shouldBeEqualTo "SampleGenericSuperClass"
    }

    @Test
    fun `class-with-parametrized-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        sut?.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parametrized-and-generic-parent-class`() {
        // given
        val sut = getSnippetFile("class-with-parametrized-and-generic-parent-class")
            .classes()
            .first()
            .parentClass

        // then
        sut?.name shouldBeEqualTo "SampleParametrizedSuperClass"
    }

    @Test
    fun `class-with-parent-interface`() {
        // given
        val sut = getSnippetFile("class-with-parent-interface")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    @Test
    fun `class-with-generic-parent-interface`() {
        // given
        val sut = getSnippetFile("class-with-generic-parent-interface")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        sut.name shouldBeEqualTo "SampleGenericSuperInterface"
    }

    @Test
    fun `class-with-parent-by-delegation`() {
        // given
        val sut = getSnippetFile("class-with-parent-by-delegation")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        sut.name shouldBeEqualTo "SampleSuperInterface"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkonameprovider/", fileName)
}
