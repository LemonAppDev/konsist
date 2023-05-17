package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.Konsist
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import java.io.File

class KoScopeForEqualsTest {
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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forequals/", fileName)
}
