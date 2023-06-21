package com.lemonappdev.konsist.core.verify.common

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.verify.assert
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.Test

class CommonTest {
    @Test
    fun `assert-test-method-name`() {
        // given
        val sut = getSnippetFile("assert-test-method-name")
            .classes()

        // then
        try {
            sut.assert { false }
        } catch (e: Exception) {
            e.message?.shouldContain("Assert 'assert-test-method-name' has failed. Invalid declarations (1)") ?: throw e
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/verify/common/snippet/", fileName)
}
