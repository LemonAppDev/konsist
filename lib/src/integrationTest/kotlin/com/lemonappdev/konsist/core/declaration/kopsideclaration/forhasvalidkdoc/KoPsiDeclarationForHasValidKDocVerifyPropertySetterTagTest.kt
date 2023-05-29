package com.lemonappdev.konsist.core.declaration.kopsideclaration.forhasvalidkdoc

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForHasValidKDocVerifyPropertySetterTagTest {
    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValues")
    fun `hasValidKDoc-with-property-setter-tag`(
        fileName: String,
        verifyPropertySetterTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyPropertySetterTag = verifyPropertySetterTag) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kopsideclaration/snippet/forhasvalidkdoc/forpropertysettertag/".toNormalizedPath(),
            fileName,
        )

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("declaration-with-kdoc-with-property-setter-tag", true, true),
            arguments("declaration-with-kdoc-with-property-setter-tag", false, true),
            arguments("declaration-with-kdoc-without-property-setter-tag", true, false),
            arguments("declaration-with-kdoc-without-property-setter-tag", false, true),
        )
    }
}
