package com.lemonappdev.konsist.core.declaration.kopsideclaration.forhasvalidkdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForHasValidKDocVerifyVersionTagTest {

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `hasValidKDoc-with-version-tag`(
        fileName: String,
        verifyVersionTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyVersionTag = verifyVersionTag) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopsideclaration/snippet/forhasvalidkdoc/forversiontag/".toNormalizedPath(),
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("declaration-with-kdoc-with-version-tag", true, true),
            arguments("declaration-with-kdoc-with-version-tag", false, true),
            arguments("declaration-with-kdoc-without-version-tag", true, false),
            arguments("declaration-with-kdoc-without-version-tag", false, true),
        )
    }
}
