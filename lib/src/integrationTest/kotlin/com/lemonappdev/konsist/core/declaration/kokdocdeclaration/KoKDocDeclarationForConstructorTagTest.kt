package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForConstructorTagTest {
    @Test
    fun `class-with-constructor-tag`() {
        // given
        val sut = getSnippetFile("class-with-constructor-tag")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            it?.constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forconstructortag/", fileName)
}
