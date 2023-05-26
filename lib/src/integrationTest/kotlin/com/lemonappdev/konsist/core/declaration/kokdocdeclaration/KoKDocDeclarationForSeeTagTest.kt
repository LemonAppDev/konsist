package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForSeeTagTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `see-tag`(
        fileName: String,
        declarationName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        assertSoftly(sut) {
            it?.seeTags?.shouldHaveSize(2)
            it?.seeTags?.get(0)?.name shouldBeEqualTo SEE
            it?.seeTags?.get(0)?.value shouldBeEqualTo "AnotherClass1"
            it?.seeTags?.get(0)?.description shouldBeEqualTo "sample description"
            it?.seeTags?.get(1)?.name shouldBeEqualTo SEE
            it?.seeTags?.get(1)?.value shouldBeEqualTo "AnotherClass2"
            it?.seeTags?.get(1)?.description shouldBeEqualTo ""
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forseetag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("class-with-see-tag", "SampleClass"),
            arguments("function-with-see-tag", "sampleMethod"),
        )
    }
}
