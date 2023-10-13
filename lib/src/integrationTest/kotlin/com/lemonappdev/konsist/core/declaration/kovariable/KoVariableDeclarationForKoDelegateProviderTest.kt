package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoDelegateProviderTest {
    @Test
    fun `variable-has-lazy-delegate`() {
        // given
        val sut = getSnippetFile("variable-has-lazy-delegate")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "lazy"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("lazy") shouldBeEqualTo true
            hasDelegate("Delegates.observable()") shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-has-no-delegate`() {
        // given
        val sut = getSnippetFile("variable-has-no-delegate")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forkodelegateprovider/", fileName)
}
