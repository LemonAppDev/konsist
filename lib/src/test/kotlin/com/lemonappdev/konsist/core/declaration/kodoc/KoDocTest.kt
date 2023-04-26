package com.lemonappdev.konsist.core.declaration.kodoc

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDocTest {
    @Test
    fun `block-tags class`() {
        // given
        val sut = getSnippetFile("block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        sut.blockTags shouldHaveSize 10
    }

    @Test
    fun `block-tags function`() {
        // given
        val sut = getSnippetFile("block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        sut.blockTags shouldHaveSize 2
    }

    @Test
    fun `block-tags property`() {
        // given
        val sut = getSnippetFile("block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        sut.blockTags shouldHaveSize 2
    }

    @Test
    fun `class-with-block-tags text and description`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
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
    fun `class-with-block-tags param tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            paramBlockTags shouldHaveSize 2
            paramBlockTags[0].name shouldBeEqualTo "@param"
            paramBlockTags[0].value shouldBeEqualTo "SampleType1"
            paramBlockTags[0].description shouldBeEqualTo "The first type parameter for this class."
            paramBlockTags[1].name shouldBeEqualTo "@param"
            paramBlockTags[1].value shouldBeEqualTo "SampleType2"
            paramBlockTags[1].description shouldBeEqualTo "The second type parameter for this class."
        }
    }

    @Test
    fun `class-with-block-tags property tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertyBlockTags shouldHaveSize 2
            propertyBlockTags[0].name shouldBeEqualTo "@property"
            propertyBlockTags[0].value shouldBeEqualTo "sampleProperty1"
            propertyBlockTags[0].description shouldBeEqualTo "The first property of the class."
            propertyBlockTags[1].name shouldBeEqualTo "@property"
            propertyBlockTags[1].value shouldBeEqualTo "sampleProperty2"
            propertyBlockTags[1].description shouldBeEqualTo "The second property of the class."
        }
    }

    @Test
    fun `class-with-block-tags constructor tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            constructorBlockTag?.name shouldBeEqualTo "@constructor"
            constructorBlockTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    @Test
    fun `class-with-block-tags throws tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            throwsBlockTags shouldHaveSize 2
            throwsBlockTags[0].name shouldBeEqualTo "@throws"
            throwsBlockTags[0].value shouldBeEqualTo "IllegalArgumentException"
            throwsBlockTags[0].description shouldBeEqualTo "First sample description"
            throwsBlockTags[1].name shouldBeEqualTo "@exception"
            throwsBlockTags[1].value shouldBeEqualTo "NullPointerException"
            throwsBlockTags[1].description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `class-with-block-tags see tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            seeBlockTags shouldHaveSize 2
            seeBlockTags[0].name shouldBeEqualTo "@see"
            seeBlockTags[0].value shouldBeEqualTo "AnotherClass1"
            seeBlockTags[0].description shouldBeEqualTo "sample description"
            seeBlockTags[1].name shouldBeEqualTo "@see"
            seeBlockTags[1].value shouldBeEqualTo "AnotherClass2"
            seeBlockTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `class-with-block-tags since tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sinceBlockTag?.name shouldBeEqualTo "@since"
            sinceBlockTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `class-with-block-tags version tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            versionBlockTag?.name shouldBeEqualTo "@version"
            versionBlockTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `class-with-block-tags sample tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sampleBlockTags shouldHaveSize 2
            sampleBlockTags[0].name shouldBeEqualTo "@sample"
            sampleBlockTags[0].value shouldBeEqualTo "SampleClass.sampleMethod"
            sampleBlockTags[0].description shouldBeEqualTo "sample description"
            sampleBlockTags[1].name shouldBeEqualTo "@sample"
            sampleBlockTags[1].value shouldBeEqualTo "SampleClass.sampleProperty"
            sampleBlockTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `class-with-block-tags suppress tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            suppressBlockTag?.name shouldBeEqualTo "@suppress"
            suppressBlockTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `class-with-block-tags author tag`() {
        // given
        val sut = getSnippetFile("class-with-block-tags")
            .classes()
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            authorBlockTags shouldHaveSize 2
            authorBlockTags[0].name shouldBeEqualTo "@author"
            authorBlockTags[0].description shouldBeEqualTo "Author1"
            authorBlockTags[1].name shouldBeEqualTo "@author"
            authorBlockTags[1].description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-block-tags text and description`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
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
    fun `function-with-block-tags param tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            paramBlockTags shouldHaveSize 2
            paramBlockTags[0].name shouldBeEqualTo "@param"
            paramBlockTags[0].value shouldBeEqualTo "sampleArgument1"
            paramBlockTags[0].description shouldBeEqualTo "The first argument."
            paramBlockTags[1].name shouldBeEqualTo "@param"
            paramBlockTags[1].value shouldBeEqualTo "sampleArgument2"
            paramBlockTags[1].description shouldBeEqualTo "The second argument."
        }
    }

    @Test
    fun `function-with-block-tags return tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            returnBlockTag?.name shouldBeEqualTo "@return"
            returnBlockTag?.description shouldBeEqualTo "The result of the computation."
        }
    }

    @Test
    fun `function-with-block-tags throws tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            throwsBlockTags shouldHaveSize 2
            throwsBlockTags[0].name shouldBeEqualTo "@throws"
            throwsBlockTags[0].value shouldBeEqualTo "IllegalArgumentException"
            throwsBlockTags[0].description shouldBeEqualTo "First sample description"
            throwsBlockTags[1].name shouldBeEqualTo "@exception"
            throwsBlockTags[1].value shouldBeEqualTo "NullPointerException"
            throwsBlockTags[1].description shouldBeEqualTo "Second sample description"
        }
    }

    @Test
    fun `function-with-block-tags sample tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sampleBlockTags shouldHaveSize 2
            sampleBlockTags[0].name shouldBeEqualTo "@sample"
            sampleBlockTags[0].value shouldBeEqualTo "SampleClass.sampleMethod"
            sampleBlockTags[0].description shouldBeEqualTo "sample description"
            sampleBlockTags[1].name shouldBeEqualTo "@sample"
            sampleBlockTags[1].value shouldBeEqualTo "SampleClass.sampleProperty"
            sampleBlockTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `function-with-block-tags receiver tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            receiverBlockTag?.name shouldBeEqualTo "@receiver"
            receiverBlockTag?.description shouldBeEqualTo "sample receiver description"
        }
    }

    @Test
    fun `function-with-block-tags see tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            seeBlockTags shouldHaveSize 2
            seeBlockTags[0].name shouldBeEqualTo "@see"
            seeBlockTags[0].value shouldBeEqualTo "AnotherClass1"
            seeBlockTags[0].description shouldBeEqualTo "sample description"
            seeBlockTags[1].name shouldBeEqualTo "@see"
            seeBlockTags[1].value shouldBeEqualTo "AnotherClass2"
            seeBlockTags[1].description shouldBeEqualTo ""
        }
    }

    @Test
    fun `function-with-block-tags author tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            authorBlockTags shouldHaveSize 2
            authorBlockTags[0].name shouldBeEqualTo "@author"
            authorBlockTags[0].description shouldBeEqualTo "Author1"
            authorBlockTags[1].name shouldBeEqualTo "@author"
            authorBlockTags[1].description shouldBeEqualTo "Author2"
        }
    }

    @Test
    fun `function-with-block-tags since tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            sinceBlockTag?.name shouldBeEqualTo "@since"
            sinceBlockTag?.description shouldBeEqualTo "1.0.0"
        }
    }

    @Test
    fun `function-with-block-tags version tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            versionBlockTag?.name shouldBeEqualTo "@version"
            versionBlockTag?.description shouldBeEqualTo "1.2.3"
        }
    }

    @Test
    fun `function-with-block-tags suppress tag`() {
        // given
        val sut = getSnippetFile("function-with-block-tags")
            .functions(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            suppressBlockTag?.name shouldBeEqualTo "@suppress"
            suppressBlockTag?.description shouldBeEqualTo "UnusedPrivateMember"
        }
    }

    @Test
    fun `property-with-block-tags text and description`() {
        // given
        val sut = getSnippetFile("property-with-block-tags")
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
    fun `property-with-block-tags propertySetter tag`() {
        // given
        val sut = getSnippetFile("property-with-block-tags")
            .properties(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertySetterBlockTag?.name shouldBeEqualTo "@propertySetter"
            propertySetterBlockTag?.description shouldBeEqualTo "Sets the value of the [name] property."
        }
    }

    @Test
    fun `property-with-block-tags propertyGetter tag`() {
        // given
        val sut = getSnippetFile("property-with-block-tags")
            .properties(includeNested = true)
            .first()
            .koDoc!!

        // then
        assertSoftly(sut) {
            propertyGetterBlockTag?.name shouldBeEqualTo "@propertyGetter"
            propertyGetterBlockTag?.description shouldBeEqualTo "Retrieves the value of the [name] property."
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kodoc/snippet/", fileName)
}
