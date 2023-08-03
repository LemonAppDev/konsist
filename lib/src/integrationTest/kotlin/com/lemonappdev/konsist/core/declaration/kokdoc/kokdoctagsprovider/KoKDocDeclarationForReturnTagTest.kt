package com.lemonappdev.konsist.core.declaration.kokdoc.kokdoctagsprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForReturnTagTest {
    @Test
    fun `function-with-return-tag`() {
        // given
        val sut = getSnippetFile("function-with-return-tag")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.returnTag?.name shouldBeEqualTo RETURN
            it?.returnTag?.description shouldBeEqualTo "The result of the computation."
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kokdocdeclaration/kokdoctagsprovider/snippet/forreturntag/", fileName)
}
