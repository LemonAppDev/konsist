package com.lemonappdev.konsist.core.declaration.kopsideclaration.forhasvalidkdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForHasValidKDocVerifyDescriptionTest {
    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `hasValidKDoc-with-description`(
        fileName: String,
        verifyDescription: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = verifyDescription) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopsideclaration/snippet/forhasvalidkdoc/fordescription/".toNormalizedPath(),
            fileName
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("declaration-without-kdoc", true, false),
            arguments("declaration-without-kdoc", false, false),
            arguments("declaration-with-empty-kdoc", true, false),
            arguments("declaration-with-empty-kdoc", false, true),
            arguments("declaration-with-kdoc-with-description", true, true),
            arguments("declaration-with-kdoc-with-description", false, true),
            arguments("declaration-with-kdoc-without-description", true, false),
            arguments("declaration-with-kdoc-without-description", false, true),
        )
    }
}
