package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForHashCodeTest {
    @Test
    fun `scopes-with-the-same-hashcode`() {
        // given
        val scope1 = getSnippetFile("scopes-with-the-same-hashcode")
        val scope2 = getSnippetFile("scopes-with-the-same-hashcode")

        // then
        scope1.hashCode() shouldBeEqualTo scope2.hashCode()
    }

    @Test
    fun `scopes-with-the-other-hashcode`() {
        // given
        val scope1 = getSnippetFile("scopes-with-the-other-hashcode")
        val scope2 = getSnippetFile("scopes-with-the-same-hashcode")

        // then
        scope1.hashCode() shouldNotBeEqualTo scope2.hashCode()
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/snippet/forhashcode/", fileName)
}
