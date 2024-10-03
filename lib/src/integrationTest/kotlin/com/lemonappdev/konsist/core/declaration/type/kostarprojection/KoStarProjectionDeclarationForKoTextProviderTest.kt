package com.lemonappdev.konsist.core.declaration.type.kostarprojection

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoStarProjectionDeclarationForKoTextProviderTest {
    @Test
    fun `star-projection-text`() {
        // given
        val sut =
            getSnippetFile("star-projection-text")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asStarProjectionDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "*"
            it?.hasTextStartingWith("*") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("*") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("*") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kostarprojection/snippet/forkotextprovider/", fileName)
}
