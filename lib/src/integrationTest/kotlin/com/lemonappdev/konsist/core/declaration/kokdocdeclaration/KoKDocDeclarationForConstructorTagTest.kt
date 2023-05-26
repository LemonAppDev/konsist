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
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoKDocDeclarationForConstructorTagTest {
    @Test
    fun `class-with-constructor-tag`() {
        // given
        val sut = getSnippetFile("class-with-constructor-tag")
            .classes()
            .first()
            .kDoc

        // then
        assertSoftly(sut) {
            it?.constructorTag?.name shouldBeEqualTo CONSTRUCTOR
            it?.constructorTag?.description shouldBeEqualTo "Creates a new instance of the [SampleClass]."
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kokdocdeclaration/snippet/forconstructortag/", fileName)
}
