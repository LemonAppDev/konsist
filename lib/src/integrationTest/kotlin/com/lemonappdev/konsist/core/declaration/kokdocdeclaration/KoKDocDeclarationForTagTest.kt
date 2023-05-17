package com.lemonappdev.konsist.core.declaration.kokdocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoKDocTag.AUTHOR
import com.lemonappdev.konsist.api.KoKDocTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoKDocTag.EXCEPTION
import com.lemonappdev.konsist.api.KoKDocTag.PARAM
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoKDocTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoKDocTag.RECEIVER
import com.lemonappdev.konsist.api.KoKDocTag.RETURN
import com.lemonappdev.konsist.api.KoKDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoKDocTag.SEE
import com.lemonappdev.konsist.api.KoKDocTag.SINCE
import com.lemonappdev.konsist.api.KoKDocTag.SUPPRESS
import com.lemonappdev.konsist.api.KoKDocTag.THROWS
import com.lemonappdev.konsist.api.KoKDocTag.VERSION
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForTagTest {
    @ParameterizedTest
    @MethodSource("provideValuesForTagsSize")
    fun `tags-size`(
        declarationName: String,
        size: Int,
    ) {
        // given
        val sut = getSnippetFile("tags")
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        sut
            ?.tags
            ?.shouldHaveSize(size)
    }

    @Test
    fun `class-with-unknown-tag`() {
        // given
        val sut = getSnippetFile("class-with-unknown-tag")
            .classes()
            .first()
            .kDoc

        // then
        val actual = { sut?.tags }
        actual shouldThrow KoInternalException::class withMessage "Unknown doc tag: @unknown, declaration:\nnull"
    }

    @Test
    fun `tags with multiline param tag`() {
        // given
        val sut = getSnippetFile("tags")
            .classes()
            .first()
            .kDoc

        // then
        sut
            ?.paramTags
            ?.get(0)
            ?.description
            .shouldBeEqualTo("First line description\nSecond line description")
    }

    @Test
    fun `tags with '@' into description`() {
        // given
        val sut = getSnippetFile("tags")
            .classes()
            .first()
            .kDoc

        // then
        sut
            ?.propertyTags
            ?.get(0)
            ?.description
            .shouldBeEqualTo("The first @property of the class.")
    }

    @ParameterizedTest
    @MethodSource("provideValuesForParamTag")
    @Suppress("detekt.LongParameterList")
    fun `param-tag`(
        fileName: String,
        declarationName: String,
        value1: String,
        description1: String,
        value2: String,
        description2: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .namedDeclarations(includeNested = true)
            .first { it.name == declarationName }
            .kDoc

        // then
        assertSoftly(sut) {
            it?.paramTags?.shouldHaveSize(2)
            it?.paramTags?.get(0)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(0)?.value shouldBeEqualTo value1
            it?.paramTags?.get(0)?.description shouldBeEqualTo description1
            it?.paramTags?.get(1)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(1)?.value shouldBeEqualTo value2
            it?.paramTags?.get(1)?.description shouldBeEqualTo description2
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `throws-tag`(
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
            it?.throwsTags?.shouldHaveSize(1)
            it?.throwsTags?.get(0)?.name shouldBeEqualTo THROWS
            it?.throwsTags?.get(0)?.value shouldBeEqualTo "IllegalArgumentException"
            it?.throwsTags?.get(0)?.description shouldBeEqualTo "First sample description"
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `exception-tag`(
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
            it?.exceptionTags?.shouldHaveSize(1)
            it?.exceptionTags?.get(0)?.name shouldBeEqualTo EXCEPTION
            it?.exceptionTags?.get(0)?.value shouldBeEqualTo "NullPointerException"
            it?.exceptionTags?.get(0)?.description shouldBeEqualTo "Second sample description"
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `since-tag`(
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
            it?.sinceTag?.name shouldBeEqualTo SINCE
            it?.sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `version-tag`(
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
            it?.versionTag?.name shouldBeEqualTo VERSION
            it?.versionTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
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

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `sample-tag`(
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
            it?.sampleTags?.shouldHaveSize(2)
            it?.sampleTags?.get(0)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(0)?.value shouldBeEqualTo "SampleClass.sampleMethod"
            it?.sampleTags?.get(0)?.description shouldBeEqualTo "sample description"
            it?.sampleTags?.get(1)?.name shouldBeEqualTo SAMPLE
            it?.sampleTags?.get(1)?.value shouldBeEqualTo "SampleClass.sampleProperty"
            it?.sampleTags?.get(1)?.description shouldBeEqualTo ""
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `author-tag`(
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
            it?.authorTags?.shouldHaveSize(2)
            it?.authorTags?.get(0)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(0)?.description shouldBeEqualTo "Author1"
            it?.authorTags?.get(1)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(1)?.description shouldBeEqualTo "Author2"
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTags")
    fun `suppress-tag`(
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
            it?.suppressTag?.name shouldBeEqualTo SUPPRESS
            it?.suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `class-with-tags property tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.propertyTags?.shouldHaveSize(2)
            it?.propertyTags?.get(0)?.name shouldBeEqualTo PROPERTY
            it?.propertyTags?.get(0)?.value shouldBeEqualTo "sampleProperty1"
            it?.propertyTags?.get(0)?.description shouldBeEqualTo "The first property of the class."
            it?.propertyTags?.get(1)?.name shouldBeEqualTo PROPERTY
            it?.propertyTags?.get(1)?.value shouldBeEqualTo "sampleProperty2"
            it?.propertyTags?.get(1)?.description shouldBeEqualTo "The second property of the class."
        }
    }

    @Test
    fun `class-with-tags constructor tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            it?.constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    @Test
    fun `function-with-tags return tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.returnTag?.name shouldBeEqualTo RETURN
            it?.returnTag?.description shouldBeEqualTo "The result of the computation."
        }
    }

    @Test
    fun `function-with-tags receiver tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.receiverTag?.name shouldBeEqualTo RECEIVER
            it?.receiverTag?.description shouldBeEqualTo "sample receiver description"
        }
    }

    @Test
    fun `property-with-tags propertySetter tag`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.propertySetterTag?.name shouldBeEqualTo PROPERTY_SETTER
            it?.propertySetterTag?.description shouldBeEqualTo "Sets the value of the [name] property."
        }
    }

    @Test
    fun `property-with-tags propertyGetter tag`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.propertyGetterTag?.name shouldBeEqualTo PROPERTY_GETTER
            it?.propertyGetterTag?.description shouldBeEqualTo "Retrieves the value of the [name] property."
        }
    }

    @Test
    fun `class-has-tags`() {
        // given
        val sut = getSnippetFile("class-has-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-tags-without-description`() {
        // given
        val sut = getSnippetFile("class-has-tags-without-description")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.description shouldBeEqualTo ""
            it?.hasTags(SINCE) shouldBeEqualTo true
            it?.hasTags(SINCE, SEE) shouldBeEqualTo true
            it?.hasTags(SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            it?.hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/fortag/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTagsSize() = listOf(
            arguments("SampleClass", 10),
            arguments("sampleMethod", 2),
            arguments("sampleProperty", 2),
            arguments("SampleClassWithoutTags", 0)
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParamTag() = listOf(
            arguments(
                "class-with-tags",
                "SampleClass",
                "SampleType1",
                "The first type parameter for this class.",
                "SampleType2",
                "The second type parameter for this class.",
            ),
            arguments(
                "function-with-tags",
                "sampleMethod",
                "sampleArgument1",
                "The first argument.",
                "sampleArgument2",
                "The second argument.",
            ),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTags() = listOf(
            arguments("class-with-tags", "SampleClass"),
            arguments("function-with-tags", "sampleMethod"),
        )
    }
}
