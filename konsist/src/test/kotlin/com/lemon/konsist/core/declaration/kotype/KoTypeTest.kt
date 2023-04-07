package com.lemon.konsist.core.declaration.kotype

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeTest {

    @Test
    fun `alias-type`() {
        // given
        val sut = getSut("alias-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.run {
            name shouldBeEqualTo "SampleType"
            aliasName shouldBeEqualTo "AliasType"
            isTypeAlias shouldBeEqualTo true
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `simple-type`() {
        // given
        val sut = getSut("simple-type")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        sut?.run {
            name shouldBeEqualTo "SampleType"
            aliasName shouldBeEqualTo null
            isTypeAlias shouldBeEqualTo false
            fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("kotype/snippet/", fileName)
}
