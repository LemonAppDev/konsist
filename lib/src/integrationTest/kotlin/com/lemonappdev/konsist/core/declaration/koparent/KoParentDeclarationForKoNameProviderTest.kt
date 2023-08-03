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
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentdeclaration/snippet/forkonameprovider/", fileName)
}
