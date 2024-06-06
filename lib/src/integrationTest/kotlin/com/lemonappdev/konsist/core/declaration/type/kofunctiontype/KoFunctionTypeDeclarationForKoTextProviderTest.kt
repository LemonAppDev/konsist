package com.lemonappdev.konsist.core.declaration.type.kofunctiontype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFunctionTypeDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-text`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type
                ?.asFunctionTypeDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "() -> SampleType"
            it?.hasTextStartingWith("() -> ") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("-> SampleType") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining(") -> S") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kofunctiontype/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("function-type-text"),
                arguments("nullable-function-type-text"),
            )
    }
}
