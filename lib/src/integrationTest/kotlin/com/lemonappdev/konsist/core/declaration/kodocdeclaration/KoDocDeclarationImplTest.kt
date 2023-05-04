package com.lemonappdev.konsist.core.declaration.kodocdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoTag.AUTHOR
import com.lemonappdev.konsist.api.KoTag.CONSTRUCTOR
import com.lemonappdev.konsist.api.KoTag.EXCEPTION
import com.lemonappdev.konsist.api.KoTag.PARAM
import com.lemonappdev.konsist.api.KoTag.PROPERTY
import com.lemonappdev.konsist.api.KoTag.PROPERTY_GETTER
import com.lemonappdev.konsist.api.KoTag.PROPERTY_SETTER
import com.lemonappdev.konsist.api.KoTag.RECEIVER
import com.lemonappdev.konsist.api.KoTag.RETURN
import com.lemonappdev.konsist.api.KoTag.SAMPLE
import com.lemonappdev.konsist.api.KoTag.SEE
import com.lemonappdev.konsist.api.KoTag.SINCE
import com.lemonappdev.konsist.api.KoTag.SUPPRESS
import com.lemonappdev.konsist.api.KoTag.THROWS
import com.lemonappdev.konsist.api.KoTag.VERSION
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
            .koDoc!!

        // then
        sut.tags shouldHaveSize 10
    }

    @Test
    fun `tags function`() {
        // given
        val sut = getSnippetFile("tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        sut.tags shouldHaveSize 2
    }

    @Test
    fun `tags property`() {
        // given
        val sut = getSnippetFile("tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        sut.tags shouldHaveSize 2
    }

    @Test
    fun `class-with-unknown-tag`() {
        // given
        val sut = getSnippetFile("class-with-unknown-tag")
            .classes()
            .first()
            .koDoc!!

        // then
        val actual = { sut.tags }
        actual shouldThrow KoInternalException::class withMessage "Unknown doc tag: @unknown, declaration:\nnull"
    }

    @Test
    fun `tags with multiline param tag`() {
        // given
        val sut = getSnippetFile("tags")
            .classes()
            .first()
            .koDoc!!

        // then
        sut
            .paramTags[0]
            .description
            .shouldBeEqualTo("First line description\nSecond line description")
    }

    @Test
    fun `tags with '@' into description`() {
        // given
        val sut = getSnippetFile("tags")
            .classes()
            .first()
            .koDoc!!

        // then
        sut
            .propertyTags[0]
            .description
            .shouldBeEqualTo("The first @property of the class.")
    }

    @Test
    fun `class-with-tags text and description`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            text shouldContain "This is a sample class that demonstrates the usage of KDoc tags."
            description shouldBeEqualTo "This is a sample class that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `class-with-tags param tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            paramTags shouldHaveSize 2
            paramTags[0].name shouldBeEqualTo PARAM
            paramTags[0].value shouldBeEqualTo "SampleType1"
            paramTags[0].description shouldBeEqualTo "The first type parameter for this class."
            paramTags[1].name shouldBeEqualTo PARAM
            paramTags[1].value shouldBeEqualTo "SampleType2"
            paramTags[1].description shouldBeEqualTo "The second type parameter for this class."
        }
    }

    @Test
    fun `class-with-tags property tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertyTags shouldHaveSize 2
            propertyTags[0].name shouldBeEqualTo PROPERTY
            propertyTags[0].value shouldBeEqualTo "sampleProperty1"
            propertyTags[0].description shouldBeEqualTo "The first property of the class."
            propertyTags[1].name shouldBeEqualTo PROPERTY
            propertyTags[1].value shouldBeEqualTo "sampleProperty2"
            propertyTags[1].description shouldBeEqualTo "The second property of the class."
        }
    }

    @Test
    fun `class-with-tags constructor tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    @Test
    fun `class-with-tags throws tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            throwsTags shouldHaveSize 1
            throwsTags[0].name shouldBeEqualTo THROWS
            throwsTags[0].value shouldBeEqualTo "IllegalArgumentException"
            throwsTags[0].description shouldBeEqualTo "First sample description"
        }
    }

    @Test
    fun `class-with-tags exception tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            exceptionTags shouldHaveSize 1
            exceptionTags[0].name shouldBeEqualTo EXCEPTION
            exceptionTags[0].value shouldBeEqualTo "NullPointerException"
            exceptionTags[0].description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `class-with-tags see tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            seeTags shouldHaveSize 2
            seeTags[0].name shouldBeEqualTo SEE
            seeTags[0].value shouldBeEqualTo "AnotherClass1"
            seeTags[0].description shouldBeEqualTo "sample description"
            seeTags[1].name shouldBeEqualTo SEE
            seeTags[1].value shouldBeEqualTo "AnotherClass2"
            seeTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `class-with-tags since tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sinceTag?.name shouldBeEqualTo SINCE
            sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `class-with-tags version tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            versionTag?.name shouldBeEqualTo VERSION
            versionTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `class-with-tags sample tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sampleTags shouldHaveSize 2
            sampleTags[0].name shouldBeEqualTo SAMPLE
            sampleTags[0].value shouldBeEqualTo "SampleClass.sampleMethod"
            sampleTags[0].description shouldBeEqualTo "sample description"
            sampleTags[1].name shouldBeEqualTo SAMPLE
            sampleTags[1].value shouldBeEqualTo "SampleClass.sampleProperty"
            sampleTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `class-with-tags suppress tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            suppressTag?.name shouldBeEqualTo SUPPRESS
            suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `class-with-tags author tag`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            authorTags shouldHaveSize 2
            authorTags[0].name shouldBeEqualTo AUTHOR
            authorTags[0].description shouldBeEqualTo "Author1"
            authorTags[1].name shouldBeEqualTo AUTHOR
            authorTags[1].description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-tags text and description`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            text shouldContain "This is a sample method that demonstrates the usage of KDoc tags."
            description shouldBeEqualTo "This is a sample method that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `function-with-tags param tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            paramTags shouldHaveSize 2
            paramTags[0].name shouldBeEqualTo PARAM
            paramTags[0].value shouldBeEqualTo "sampleArgument1"
            paramTags[0].description shouldBeEqualTo "The first argument."
            paramTags[1].name shouldBeEqualTo PARAM
            paramTags[1].value shouldBeEqualTo "sampleArgument2"
            paramTags[1].description shouldBeEqualTo "The second argument."
        }
    }

    @Test
    fun `function-with-tags return tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            returnTag?.name shouldBeEqualTo RETURN
            returnTag?.description shouldBeEqualTo "The result of the computation."
        }
    }

    @Test
    fun `function-with-tags throws tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            throwsTags shouldHaveSize 1
            throwsTags[0].name shouldBeEqualTo THROWS
            throwsTags[0].value shouldBeEqualTo "IllegalArgumentException"
            throwsTags[0].description shouldBeEqualTo "First sample description"
        }
    }

    @Test
    fun `function-with-tags exception tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            exceptionTags shouldHaveSize 1
            exceptionTags[0].name shouldBeEqualTo EXCEPTION
            exceptionTags[0].value shouldBeEqualTo "NullPointerException"
            exceptionTags[0].description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `function-with-tags sample tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sampleTags shouldHaveSize 2
            sampleTags[0].name shouldBeEqualTo SAMPLE
            sampleTags[0].value shouldBeEqualTo "SampleClass.sampleMethod"
            sampleTags[0].description shouldBeEqualTo "sample description"
            sampleTags[1].name shouldBeEqualTo SAMPLE
            sampleTags[1].value shouldBeEqualTo "SampleClass.sampleProperty"
            sampleTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `function-with-tags receiver tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            receiverTag?.name shouldBeEqualTo RECEIVER
            receiverTag?.description shouldBeEqualTo "sample receiver description"
        }
    }

    @Test
    fun `function-with-tags see tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            seeTags shouldHaveSize 2
            seeTags[0].name shouldBeEqualTo SEE
            seeTags[0].value shouldBeEqualTo "AnotherClass1"
            seeTags[0].description shouldBeEqualTo "sample description"
            seeTags[1].name shouldBeEqualTo SEE
            seeTags[1].value shouldBeEqualTo "AnotherClass2"
            seeTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `function-with-tags author tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            authorTags shouldHaveSize 2
            authorTags[0].name shouldBeEqualTo AUTHOR
            authorTags[0].description shouldBeEqualTo "Author1"
            authorTags[1].name shouldBeEqualTo AUTHOR
            authorTags[1].description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-tags since tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sinceTag?.name shouldBeEqualTo SINCE
            sinceTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `function-with-tags version tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            versionTag?.name shouldBeEqualTo VERSION
            versionTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `function-with-tags suppress tag`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            suppressTag?.name shouldBeEqualTo SUPPRESS
            suppressTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `property-with-tags text and description`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            text shouldContain "This is a sample property that demonstrates the usage of KDoc tags."
            description shouldBeEqualTo "This is a sample property that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `property-with-tags propertySetter tag`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertySetterTag?.name shouldBeEqualTo PROPERTY_SETTER
            propertySetterTag?.description shouldBeEqualTo "Sets the value of the [name] property."
        }
    }

    @Test
    fun `property-with-tags propertyGetter tag`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertyGetterTag?.name shouldBeEqualTo PROPERTY_GETTER
            propertyGetterTag?.description shouldBeEqualTo "Retrieves the value of the [name] property."
        }
    }

    @Test
    fun `class-has-tags`() {
        // given
        val sut = getSnippetFile("class-has-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            hasTags(SINCE) shouldBeEqualTo true
            hasTags(SINCE, SEE) shouldBeEqualTo true
            hasTags(SAMPLE) shouldBeEqualTo false
            hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-tags-without-description`() {
        // given
        val sut = getSnippetFile("class-has-tags-without-description")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            description shouldBeEqualTo ""
            hasTags(SINCE) shouldBeEqualTo true
            hasTags(SINCE, SEE) shouldBeEqualTo true
            hasTags(SAMPLE) shouldBeEqualTo false
            hasTags(SINCE, SAMPLE) shouldBeEqualTo false
            hasTags(SINCE, SEE, SAMPLE) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodocdeclaration/snippet/", fileName)
}
