package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoArgumentProviderTest {
    @Test
    fun `enum-const-without-arguments`() {
        // given
        val sut = getSnippetFile("enum-const-without-arguments")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-with-constructor-invocation-without-arguments`() {
        // given
        val sut = getSnippetFile("enum-const-with-constructor-invocation-without-arguments")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            arguments shouldBeEqualTo emptyList()
            numArguments shouldBeEqualTo 0
            hasArguments() shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-with-one-argument`() {
        // given
        val sut = getSnippetFile("enum-const-with-one-argument")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("0")
            numArguments shouldBeEqualTo 1
            hasArguments() shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-with-two-arguments`() {
        // given
        val sut = getSnippetFile("enum-const-with-two-arguments")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf("0", "false")
            numArguments shouldBeEqualTo 2
            hasArguments() shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-with-multiline-string-argument`() {
        // given
        val sut = getSnippetFile("enum-const-with-multiline-string-argument")
            .classes()
            .enumConstants
            .first()

        // then
        assertSoftly(sut) {
            arguments.map { it.value } shouldBeEqualTo listOf(
                "\"\"\"first line\n    second line\"\"\""
            )
            numArguments shouldBeEqualTo 1
            hasArguments() shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoargument/", fileName)
}
