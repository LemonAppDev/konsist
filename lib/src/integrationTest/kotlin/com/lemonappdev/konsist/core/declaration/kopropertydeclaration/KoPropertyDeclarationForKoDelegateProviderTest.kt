package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoDelegateProviderTest {
    @Test
    fun `property-has-lazy-delegate`() {
        // given
        val sut = getSnippetFile("property-has-lazy-delegate")
            .properties()
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
    fun `property-has-no-delegate`() {
        // given
        val sut = getSnippetFile("property-has-no-delegate")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkodelegateprovider/", fileName)
}
