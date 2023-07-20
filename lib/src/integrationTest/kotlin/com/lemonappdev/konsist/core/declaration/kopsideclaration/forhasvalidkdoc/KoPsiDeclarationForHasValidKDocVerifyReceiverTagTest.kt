package com.lemonappdev.konsist.core.declaration.kopsideclaration.forhasvalidkdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForHasValidKDocVerifyReceiverTagTest {

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `hasValidKDoc-with-receiver-tag`(
        fileName: String,
        verifyReceiverTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .filterIsInstance<KoKDocProvider>()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyReceiverTag = verifyReceiverTag) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forhasvalidkdoc/forreceivertag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("declaration-with-kdoc-with-receiver-tag", true, true),
            arguments("declaration-with-kdoc-with-receiver-tag", false, true),
            arguments("declaration-with-kdoc-without-receiver-tag", true, false),
            arguments("declaration-with-kdoc-without-receiver-tag", false, true),
        )
    }
}
