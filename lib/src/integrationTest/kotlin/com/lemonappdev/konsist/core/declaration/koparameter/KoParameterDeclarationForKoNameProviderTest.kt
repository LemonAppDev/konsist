package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoNameProviderTest {
    @Test
    fun `parameter-in-constructor-name`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-name")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "sampleParameter"
            it?.hasNameStartingWith("sample") shouldBeEqualTo true
            it?.hasNameStartingWith("Other") shouldBeEqualTo false
            it?.hasNameEndingWith("meter") shouldBeEqualTo true
            it?.hasNameEndingWith("other") shouldBeEqualTo false
            it?.hasNameContaining("lePar") shouldBeEqualTo true
            it?.hasNameContaining("lepar") shouldBeEqualTo false
            it?.hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            it?.hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-function-invocation-name`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-name")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "sampleParameter"
            hasNameStartingWith("sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameEndingWith("meter") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("lePar") shouldBeEqualTo true
            hasNameContaining("lepar") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkonameprovider/", fileName)
}
