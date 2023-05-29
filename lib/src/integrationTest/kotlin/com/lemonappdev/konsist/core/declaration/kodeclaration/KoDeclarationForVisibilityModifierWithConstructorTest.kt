package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForVisibilityModifierWithConstructorTest {
    @Suppress("detekt.LongParameterList")
    @ParameterizedTest
    @MethodSource("provideValuesForPrimaryConstructor")
    fun `visibility-modifiers-for-primary-constructor`(
        fileName: String,
        isPublicByDefault: Boolean,
        isPublic: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isInternal: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo isPublicByDefault
            it?.hasPublicModifier() shouldBeEqualTo isPublic
            it?.hasPrivateModifier() shouldBeEqualTo isPrivate
            it?.hasProtectedModifier() shouldBeEqualTo isProtected
            it?.hasInternalModifier() shouldBeEqualTo isInternal
        }
    }

    @Suppress("detekt.LongParameterList")
    @ParameterizedTest
    @MethodSource("provideValuesForSecondaryConstructor")
    fun `visibility-modifiers-for-secondary-constructor`(
        fileName: String,
        isPublicByDefault: Boolean,
        isPublic: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isInternal: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.isPublicOrDefault() shouldBeEqualTo isPublicByDefault
            it.hasPublicModifier() shouldBeEqualTo isPublic
            it.hasPrivateModifier() shouldBeEqualTo isPrivate
            it.hasProtectedModifier() shouldBeEqualTo isProtected
            it.hasInternalModifier() shouldBeEqualTo isInternal
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forvisibilitymodifierwithconstructor/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForPrimaryConstructor() = listOf(
            arguments("primary-constructor-has-no-visibility-modifier", true, false, false, false, false),
            arguments("primary-constructor-has-public-visibility-modifier", true, true, false, false, false),
            arguments("primary-constructor-has-private-visibility-modifier", false, false, true, false, false),
            arguments("primary-constructor-has-protected-visibility-modifier", false, false, false, true, false),
            arguments("primary-constructor-has-internal-visibility-modifier", false, false, false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSecondaryConstructor() = listOf(
            arguments("secondary-constructor-has-no-visibility-modifier", true, false, false, false, false),
            arguments("secondary-constructor-has-public-visibility-modifier", true, true, false, false, false),
            arguments("secondary-constructor-has-private-visibility-modifier", false, false, true, false, false),
            arguments("secondary-constructor-has-protected-visibility-modifier", false, false, false, true, false),
            arguments("secondary-constructor-has-internal-visibility-modifier", false, false, false, false, true),
        )
    }
}
