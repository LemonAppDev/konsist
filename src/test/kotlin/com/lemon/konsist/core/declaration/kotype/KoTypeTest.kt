package com.lemon.konsist.core.declaration.kotype

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeTest {

    @Test
    fun `type-with-alias`() {
        // given
        val sut = getSut("type-with-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.explicitType

        // then
        with(sut) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo "AliasType"
            this?.isTypeAlias shouldBeEqualTo true
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `type-without-alias`() {
        // given
        val sut = getSut("type-without-alias")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.explicitType

        // then
        with(sut) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo null
            this?.isTypeAlias shouldBeEqualTo false
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("kotype/snippet/$fileName.kttxt")
}
