package com.lemonappdev.konsist.core.declaration.kodocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoDocTag.AUTHOR
import com.lemonappdev.konsist.api.KoDocTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoDocTag.EXCEPTION
import com.lemonappdev.konsist.api.KoDocTag.PARAM
import com.lemonappdev.konsist.api.KoDocTag.PROPERTY
import com.lemonappdev.konsist.api.KoDocTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoDocTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoDocTag.RECEIVER
import com.lemonappdev.konsist.api.KoDocTag.RETURN
import com.lemonappdev.konsist.api.KoDocTag.SAMPLE
import com.lemonappdev.konsist.api.KoDocTag.SEE
import com.lemonappdev.konsist.api.KoDocTag.SINCE
import com.lemonappdev.konsist.api.KoDocTag.SUPPRESS
import com.lemonappdev.konsist.api.KoDocTag.THROWS
import com.lemonappdev.konsist.api.KoDocTag.VERSION
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoDocDeclarationImplTest {
    @Test
    fun `tags class`() {
        // given
        val sut = getSnippetFile("tags")
            .classes()
            .first()
            .koDoc

        // then
        sut
            ?.tags
            ?.shouldHaveSize(10)
    }

    @Test
    fun `tags function`() {
        // given
        val sut = getSnippetFile("tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        sut
            ?.tags
            ?.shouldHaveSize(2)
    }

    @Test
    fun `tags property`() {
        // given
        val sut = getSnippetFile("tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        sut
            ?.tags
            ?.shouldHaveSize(2)
    }

    @Test
    fun `class-with-unknown-tag`() {
        // given
        val sut = getSnippetFile("class-with-unknown-tag")
            .classes()
            .first()
            .koDoc

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
            .koDoc

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
            .koDoc

        // then
        sut
            ?.propertyTags
            ?.get(0)
            ?.description
            .shouldBeEqualTo("The first @property of the class.")
    }

    @Test
    fun `class-with-tags text and description`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample class that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample class that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `class-with-tags param tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.paramTags?.shouldHaveSize(2)
            it?.paramTags?.get(0)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(0)?.value shouldBeEqualTo "SampleType1"
            it?.paramTags?.get(0)?.description shouldBeEqualTo "The first type parameter for this class."
            it?.paramTags?.get(1)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(1)?.value shouldBeEqualTo "SampleType2"
            it?.paramTags?.get(1)?.description shouldBeEqualTo "The second type parameter for this class."
        }
    }

    @Test
    fun `class-with-tags property tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

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
            .koDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            it?.constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    @Test
    fun `class-with-tags throws tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.throwsTags?.shouldHaveSize(1)
            it?.throwsTags?.get(0)?.name shouldBeEqualTo THROWS
            it?.throwsTags?.get(0)?.value shouldBeEqualTo "IllegalArgumentException"
            it?.throwsTags?.get(0)?.description shouldBeEqualTo "First sample description"
        }
    }

    @Test
    fun `class-with-tags exception tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.exceptionTags?.shouldHaveSize(1)
            it?.exceptionTags?.get(0)?.name shouldBeEqualTo EXCEPTION
            it?.exceptionTags?.get(0)?.value shouldBeEqualTo "NullPointerException"
            it?.exceptionTags?.get(0)?.description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `class-with-tags see tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

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

    @Test
    fun `class-with-tags since tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.sinceTag?.name shouldBeEqualTo SINCE
            it?.sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `class-with-tags version tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.versionTag?.name shouldBeEqualTo VERSION
            it?.versionTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `class-with-tags sample tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

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

    @Test
    fun `class-with-tags suppress tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.suppressTag?.name shouldBeEqualTo SUPPRESS
            it?.suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `class-with-tags author tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.authorTags?.shouldHaveSize(2)
            it?.authorTags?.get(0)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(0)?.description shouldBeEqualTo "Author1"
            it?.authorTags?.get(1)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(1)?.description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-tags text and description`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample method that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample method that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `function-with-tags param tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.paramTags?.shouldHaveSize(2)
            it?.paramTags?.get(0)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(0)?.value shouldBeEqualTo "sampleArgument1"
            it?.paramTags?.get(0)?.description shouldBeEqualTo "The first argument."
            it?.paramTags?.get(1)?.name shouldBeEqualTo PARAM
            it?.paramTags?.get(1)?.value shouldBeEqualTo "sampleArgument2"
            it?.paramTags?.get(1)?.description shouldBeEqualTo "The second argument."
        }
    }

    @Test
    fun `function-with-tags return tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.returnTag?.name shouldBeEqualTo RETURN
            it?.returnTag?.description shouldBeEqualTo "The result of the computation."
        }
    }

    @Test
    fun `function-with-tags throws tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.throwsTags?.shouldHaveSize(1)
            it?.throwsTags?.get(0)?.name shouldBeEqualTo THROWS
            it?.throwsTags?.get(0)?.value shouldBeEqualTo "IllegalArgumentException"
            it?.throwsTags?.get(0)?.description shouldBeEqualTo "First sample description"
        }
    }

    @Test
    fun `function-with-tags exception tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.exceptionTags?.shouldHaveSize(1)
            it?.exceptionTags?.get(0)?.name shouldBeEqualTo EXCEPTION
            it?.exceptionTags?.get(0)?.value shouldBeEqualTo "NullPointerException"
            it?.exceptionTags?.get(0)?.description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `function-with-tags sample tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

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

    @Test
    fun `function-with-tags receiver tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.receiverTag?.name shouldBeEqualTo RECEIVER
            it?.receiverTag?.description shouldBeEqualTo "sample receiver description"
        }
    }

    @Test
    fun `function-with-tags see tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

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

    @Test
    fun `function-with-tags author tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.authorTags?.shouldHaveSize(2)
            it?.authorTags?.get(0)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(0)?.description shouldBeEqualTo "Author1"
            it?.authorTags?.get(1)?.name shouldBeEqualTo AUTHOR
            it?.authorTags?.get(1)?.description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-tags since tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.sinceTag?.name shouldBeEqualTo SINCE
            it?.sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `function-with-tags version tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.versionTag?.name shouldBeEqualTo VERSION
            it?.versionTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `function-with-tags suppress tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.suppressTag?.name shouldBeEqualTo SUPPRESS
            it?.suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `property-with-tags text and description`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .koDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample property that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample property that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `property-with-tags propertySetter tag`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .koDoc

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
            .koDoc

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
            .koDoc

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
            .koDoc

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
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodocdeclaration/snippet/", fileName)
}
