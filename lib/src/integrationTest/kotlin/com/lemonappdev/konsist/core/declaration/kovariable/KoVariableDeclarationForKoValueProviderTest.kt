package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoValueProviderTest {
    @Test
    fun `variable-has-value`() {
        // given
        val sut = getSnippetFile("variable-has-value")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "text"
            hasValue() shouldBeEqualTo true
            hasValue("text") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-with-delegation-has-no-value`() {
        // given
        val sut = getSnippetFile("variable-with-delegation-has-no-value")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo null
            hasValue() shouldBeEqualTo false
            hasValue("0") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkovalueprovider/", fileName)
}
