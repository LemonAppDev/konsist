package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPsiDeclarationForKDocTest {
    @Test
    fun `declaration-with-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-with-kdoc")
            .declarations()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldNotBeEqualTo null
            hasKDoc() shouldBeEqualTo true
        }
    }

    @Test
    fun `declaration-without-kdoc`() {
        // given
        val sut = getSnippetFile("declaration-without-kdoc")
            .declarations()
            .first()

        // then
        assertSoftly(sut) {
            kDoc shouldBeEqualTo null
            hasKDoc() shouldBeEqualTo false
        }
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForDescription")
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

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForParamTag")
    fun `hasValidKDoc-with-param-tag`(
        fileName: String,
        verifyParamTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyParamTag = verifyParamTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForReturnTag")
    fun `hasValidKDoc-with-return-tag`(
        fileName: String,
        verifyReturnTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyReturnTag = verifyReturnTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForConstructorTag")
    fun `hasValidKDoc-with-constructor-tag`(
        fileName: String,
        verifyConstructorTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyConstructorTag = verifyConstructorTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForReceiverTag")
    fun `hasValidKDoc-with-receiver-tag`(
        fileName: String,
        verifyReceiverTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyReceiverTag = verifyReceiverTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForPropertyTag")
    fun `hasValidKDoc-with-property-tag`(
        fileName: String,
        verifyPropertyTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyPropertyTag = verifyPropertyTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForThrowsTag")
    fun `hasValidKDoc-with-throws-tag`(
        fileName: String,
        verifyThrowsTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyThrowsTag = verifyThrowsTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForExceptionTag")
    fun `hasValidKDoc-with-exception-tag`(
        fileName: String,
        verifyExceptionTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyExceptionTag = verifyExceptionTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForSampleTag")
    fun `hasValidKDoc-with-sample-tag`(
        fileName: String,
        verifySampleTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifySampleTag = verifySampleTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForSeeTag")
    fun `hasValidKDoc-with-see-tag`(
        fileName: String,
        verifySeeTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifySeeTag = verifySeeTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForAuthorTag")
    fun `hasValidKDoc-with-author-tag`(
        fileName: String,
        verifyAuthorTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyAuthorTag = verifyAuthorTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForSinceTag")
    fun `hasValidKDoc-with-since-tag`(
        fileName: String,
        verifySinceTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifySinceTag = verifySinceTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForSuppressTag")
    fun `hasValidKDoc-with-suppress-tag`(
        fileName: String,
        verifySuppressTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifySuppressTag = verifySuppressTag) shouldBeEqualTo value
    }

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForVersionTag")
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

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForPropertySetterTag")
    fun `hasValidKDoc-with-propertySetter-tag`(
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

    // We used interface in snippet, because it doesn't override any method used in hasValidKDoc().
    // It may changed in the future.
    @ParameterizedTest
    @MethodSource("provideValuesForPropertyGetterTag")
    fun `hasValidKDoc-with-propertyGetter-tag`(
        fileName: String,
        verifyPropertyGetterTag: Boolean,
        value: Boolean,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .declarations()
            .first()

        // then
        sut.hasValidKDoc(verifyDescription = false, verifyPropertyGetterTag = verifyPropertyGetterTag) shouldBeEqualTo value
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forkdoc/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDescription() = listOf(
            arguments("declaration-without-kdoc", true, false),
            arguments("declaration-without-kdoc", false, false),
            arguments("declaration-with-empty-kdoc", true, false),
            arguments("declaration-with-empty-kdoc", false, true),
            arguments("declaration-with-kdoc-with-description", true, true),
            arguments("declaration-with-kdoc-with-description", false, true),
            arguments("declaration-with-kdoc-without-description", true, false),
            arguments("declaration-with-kdoc-without-description", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParamTag() = listOf(
            arguments("declaration-with-kdoc-with-param-tags", true, false),
            arguments("declaration-with-kdoc-with-param-tags", false, true),
            arguments("declaration-with-kdoc-without-param-tags", true, true),
            arguments("declaration-with-kdoc-without-param-tags", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForReturnTag() = listOf(
            arguments("declaration-with-kdoc-with-return-tag", true, true),
            arguments("declaration-with-kdoc-with-return-tag", false, true),
            arguments("declaration-with-kdoc-without-return-tag", true, false),
            arguments("declaration-with-kdoc-without-return-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForConstructorTag() = listOf(
            arguments("declaration-with-kdoc-with-constructor-tag", true, true),
            arguments("declaration-with-kdoc-with-constructor-tag", false, true),
            arguments("declaration-with-kdoc-without-constructor-tag", true, false),
            arguments("declaration-with-kdoc-without-constructor-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForReceiverTag() = listOf(
            arguments("declaration-with-kdoc-with-receiver-tag", true, true),
            arguments("declaration-with-kdoc-with-receiver-tag", false, true),
            arguments("declaration-with-kdoc-without-receiver-tag", true, false),
            arguments("declaration-with-kdoc-without-receiver-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForPropertyTag() = listOf(
            arguments("declaration-with-kdoc-with-property-tags", true, false),
            arguments("declaration-with-kdoc-with-property-tags", false, true),
            arguments("declaration-with-kdoc-without-property-tags", true, true),
            arguments("declaration-with-kdoc-without-property-tags", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForThrowsTag() = listOf(
            arguments("declaration-with-kdoc-with-throws-tag", true, true),
            arguments("declaration-with-kdoc-with-throws-tag", false, true),
            arguments("declaration-with-kdoc-without-throws-tag", true, false),
            arguments("declaration-with-kdoc-without-throws-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForExceptionTag() = listOf(
            arguments("declaration-with-kdoc-with-exception-tag", true, true),
            arguments("declaration-with-kdoc-with-exception-tag", false, true),
            arguments("declaration-with-kdoc-without-exception-tag", true, false),
            arguments("declaration-with-kdoc-without-exception-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSampleTag() = listOf(
            arguments("declaration-with-kdoc-with-sample-tag", true, true),
            arguments("declaration-with-kdoc-with-sample-tag", false, true),
            arguments("declaration-with-kdoc-without-sample-tag", true, false),
            arguments("declaration-with-kdoc-without-sample-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSeeTag() = listOf(
            arguments("declaration-with-kdoc-with-see-tag", true, true),
            arguments("declaration-with-kdoc-with-see-tag", false, true),
            arguments("declaration-with-kdoc-without-see-tag", true, false),
            arguments("declaration-with-kdoc-without-see-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForAuthorTag() = listOf(
            arguments("declaration-with-kdoc-with-author-tag", true, true),
            arguments("declaration-with-kdoc-with-author-tag", false, true),
            arguments("declaration-with-kdoc-without-author-tag", true, false),
            arguments("declaration-with-kdoc-without-author-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSinceTag() = listOf(
            arguments("declaration-with-kdoc-with-since-tag", true, true),
            arguments("declaration-with-kdoc-with-since-tag", false, true),
            arguments("declaration-with-kdoc-without-since-tag", true, false),
            arguments("declaration-with-kdoc-without-since-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForSuppressTag() = listOf(
            arguments("declaration-with-kdoc-with-suppress-tag", true, true),
            arguments("declaration-with-kdoc-with-suppress-tag", false, true),
            arguments("declaration-with-kdoc-without-suppress-tag", true, false),
            arguments("declaration-with-kdoc-without-suppress-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForVersionTag() = listOf(
            arguments("declaration-with-kdoc-with-version-tag", true, true),
            arguments("declaration-with-kdoc-with-version-tag", false, true),
            arguments("declaration-with-kdoc-without-version-tag", true, false),
            arguments("declaration-with-kdoc-without-version-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForPropertySetterTag() = listOf(
            arguments("declaration-with-kdoc-with-property-setter-tag", true, true),
            arguments("declaration-with-kdoc-with-property-setter-tag", false, true),
            arguments("declaration-with-kdoc-without-property-setter-tag", true, false),
            arguments("declaration-with-kdoc-without-property-setter-tag", false, true),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForPropertyGetterTag() = listOf(
            arguments("declaration-with-kdoc-with-property-getter-tag", true, true),
            arguments("declaration-with-kdoc-with-property-getter-tag", false, true),
            arguments("declaration-with-kdoc-without-property-getter-tag", true, false),
            arguments("declaration-with-kdoc-without-property-getter-tag", false, true),
        )
    }
}
