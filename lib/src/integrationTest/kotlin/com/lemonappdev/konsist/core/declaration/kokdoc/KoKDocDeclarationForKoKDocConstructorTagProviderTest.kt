package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoKDocConstructorTagProviderTest {
    @Test
    fun `kdoc-without-constructor-tag`() {
        // given
        val sut =
            getSnippetFile("kdoc-without-constructor-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag shouldBeEqualTo null
            it?.hasConstructorTag shouldBeEqualTo false
        }
    }

    @Test
    fun `class-with-constructor-tag`() {
        // given
        val sut =
            getSnippetFile("class-with-constructor-tag")
                .classes()
                .first()
                .kDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            it?.constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
            it?.hasConstructorTag shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdoc/snippet/forkokdocconstructortagprovider/", fileName)
}
