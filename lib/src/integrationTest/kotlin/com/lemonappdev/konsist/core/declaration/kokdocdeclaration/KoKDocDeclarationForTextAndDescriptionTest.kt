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
import org.amshove.kluent.shouldContain
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoKDocDeclarationForTextAndDescriptionTest {
    @Test
    fun `class-with-tags text and description`() {
        // given
        val sut = getSnippetFile("class-with-tags")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample class that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample class that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `function-with-tags text and description`() {
        // given
        val sut = getSnippetFile("function-with-tags")
            .functions(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample method that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample method that demonstrates the usage of KDoc tags."
        }
    }

    @Test
    fun `property-with-tags text and description`() {
        // given
        val sut = getSnippetFile("property-with-tags")
            .properties(includeNested = true)
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.text?.shouldContain("This is a sample property that demonstrates the usage of KDoc tags.")
            it?.description shouldBeEqualTo "This is a sample property that demonstrates the usage of KDoc tags."
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/fortextanddescription/", fileName)
}
