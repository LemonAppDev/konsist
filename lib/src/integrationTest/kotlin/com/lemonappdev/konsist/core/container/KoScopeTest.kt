package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeTest {
    // Other methods from KoScope (`slice`, `plus` etc.) tests
    // are implemented in the test\konsist-path-tester project.

    @Test
    fun `scopes-are-equal`() {
        // given
        val scope1 = getSnippetFile("scopes-are-equal")
        val scope2 = getSnippetFile("scopes-are-equal")

        // then
        scope1 shouldBeEqualTo scope2
    }

    @Test
    fun `scopes-are-not-equal`() {
        // given
        val scope1 = getSnippetFile("scopes-are-not-equal")
        val scope2 = getSnippetFile("scopes-are-equal")

        // then
        scope1 shouldNotBeEqualTo scope2
    }

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

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/container/snippet/forgeneral/", fileName)
}
