package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoPropertyDelegateProviderTest {
    @Test
    fun `property-has-lazy-delegate`() {
        // given
        val sut =
            getSnippetFile("property-has-lazy-delegate")
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
        val sut =
            getSnippetFile("property-has-no-delegate")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("lazy") shouldBeEqualTo false
        }
    }

    @Test
    fun `property-defined-in-constructor-has-no-delegate`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-delegate")
                .classes()
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/koproperty/snippet/forkopropertydelegateprovider/", fileName)
}
