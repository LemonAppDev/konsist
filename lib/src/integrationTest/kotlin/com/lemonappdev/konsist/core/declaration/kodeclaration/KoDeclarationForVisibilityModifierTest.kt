package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoDeclarationForVisibilityModifierTest {
    @Suppress("detekt.LongParameterList")
    @ParameterizedTest
    @MethodSource("provideValuesForDeclaration")
    fun `visibility-modifiers-for-declarations`(
        fileName: String,
        declarationName: String,
        isPublicByDefault: Boolean,
        isPublic: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isInternal: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations(includeNested = true)
            .first { it.name == declarationName }

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo isPublicByDefault
            hasPublicModifier() shouldBeEqualTo isPublic
            hasPrivateModifier() shouldBeEqualTo isPrivate
            hasProtectedModifier() shouldBeEqualTo isProtected
            hasInternalModifier() shouldBeEqualTo isInternal
        }
    }

    @Suppress("detekt.LongParameterList")
    @ParameterizedTest
    @MethodSource("provideValuesForTypeAlias")
    fun `visibility-modifiers-for-type-aliases`(
        fileName: String,
        isPublicByDefault: Boolean,
        isPublic: Boolean,
        isPrivate: Boolean,
        isProtected: Boolean,
        isInternal: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo isPublicByDefault
            hasPublicModifier() shouldBeEqualTo isPublic
            hasPrivateModifier() shouldBeEqualTo isPrivate
            hasProtectedModifier() shouldBeEqualTo isProtected
            hasInternalModifier() shouldBeEqualTo isInternal
        }
    }

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
        getSnippetKoScope("core/declaration/kodeclaration/snippet/forvisibilitymodifier/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDeclaration() = listOf(
            arguments("class-has-no-visibility-modifier", "SampleClass", true, false, false, false, false),
            arguments("class-has-public-visibility-modifier", "SampleClass", true, true, false, false, false),
            arguments("class-has-private-visibility-modifier", "SampleClass", false, false, true, false, false),
            arguments("class-has-protected-visibility-modifier", "SampleClass", false, false, false, true, false),
            arguments("class-has-internal-visibility-modifier", "SampleClass", false, false, false, false, true),
            arguments("companion-object-has-no-visibility-modifier", "SampleCompanionObject", true, false, false, false, false),
            arguments("companion-object-has-public-visibility-modifier", "SampleCompanionObject", true, true, false, false, false),
            arguments("companion-object-has-private-visibility-modifier", "SampleCompanionObject", false, false, true, false, false),
            arguments("companion-object-has-protected-visibility-modifier", "SampleCompanionObject", false, false, false, true, false),
            arguments("companion-object-has-internal-visibility-modifier", "SampleCompanionObject", false, false, false, false, true),
            arguments("function-has-no-visibility-modifier", "sampleFunction", true, false, false, false, false),
            arguments("function-has-public-visibility-modifier", "sampleFunction", true, true, false, false, false),
            arguments("function-has-private-visibility-modifier", "sampleFunction", false, false, true, false, false),
            arguments("function-has-protected-visibility-modifier", "sampleFunction", false, false, false, true, false),
            arguments("function-has-internal-visibility-modifier", "sampleFunction", false, false, false, false, true),
            arguments("interface-has-no-visibility-modifier", "SampleInterface", true, false, false, false, false),
            arguments("interface-has-public-visibility-modifier", "SampleInterface", true, true, false, false, false),
            arguments("interface-has-private-visibility-modifier", "SampleInterface", false, false, true, false, false),
            arguments("interface-has-protected-visibility-modifier", "SampleInterface", false, false, false, true, false),
            arguments("interface-has-internal-visibility-modifier", "SampleInterface", false, false, false, false, true),
            arguments("object-has-no-visibility-modifier", "SampleObject", true, false, false, false, false),
            arguments("object-has-public-visibility-modifier", "SampleObject", true, true, false, false, false),
            arguments("object-has-private-visibility-modifier", "SampleObject", false, false, true, false, false),
            arguments("object-has-protected-visibility-modifier", "SampleObject", false, false, false, true, false),
            arguments("object-has-internal-visibility-modifier", "SampleObject", false, false, false, false, true),
            arguments("property-has-no-visibility-modifier", "sampleProperty", true, false, false, false, false),
            arguments("property-has-public-visibility-modifier", "sampleProperty", true, true, false, false, false),
            arguments("property-has-private-visibility-modifier", "sampleProperty", false, false, true, false, false),
            arguments("property-has-protected-visibility-modifier", "sampleProperty", false, false, false, true, false),
            arguments("property-has-internal-visibility-modifier", "sampleProperty", false, false, false, false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTypeAlias() = listOf(
            arguments("typealias-has-no-visibility-modifier", true, false, false, false, false),
            arguments("typealias-has-public-visibility-modifier", true, true, false, false, false),
            arguments("typealias-has-private-visibility-modifier", false, false, true, false, false),
            arguments("typealias-has-protected-visibility-modifier", false, false, false, true, false),
            arguments("typealias-has-internal-visibility-modifier", false, false, false, false, true),
        )

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
