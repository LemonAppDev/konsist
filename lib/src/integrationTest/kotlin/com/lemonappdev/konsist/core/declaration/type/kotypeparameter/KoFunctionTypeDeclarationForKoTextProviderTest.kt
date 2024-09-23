package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeParameterDeclarationForKoTextProviderTest {
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `type-text`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
.functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotypeparameter/snippet/forkotextprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() =
            listOf(
                arguments("type-parameter-text"),
                arguments("nullable-type-parameter-text"),
            )
    }
}
