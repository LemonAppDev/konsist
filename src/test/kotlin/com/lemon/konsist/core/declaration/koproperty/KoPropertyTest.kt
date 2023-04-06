package com.lemon.konsist.core.declaration.koproperty

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyTest {
    @Test
    fun `property-is-val`() {
        // given
        val sut = getSut("property-is-val")
            .properties()
            .first()

        // then
        with(sut) {
            isVal shouldBeEqualTo true
            isVar shouldBeEqualTo false
            isConst shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-var`() {
        // given
        val sut = getSut("property-is-var")
            .properties()
            .first()

        // then
        with(sut) {
            isVal shouldBeEqualTo false
            isVar shouldBeEqualTo true
            isConst shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-const`() {
        // given
        val sut = getSut("property-is-const")
            .properties(includeNested = true)
            .first()

        // then
        with(sut) {
            isVal shouldBeEqualTo true
            isVar shouldBeEqualTo false
            isConst shouldBeEqualTo true
        }
    }

    @Test
    fun `property-with-type`() {
        // given
        val sut = getSut("property-with-type")
            .properties(includeNested = true)
            .first()

        // then
        with(sut.explicitType) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo null
            this?.isTypeAlias shouldBeEqualTo false
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `property-with-alias-type`() {
        // given
        val sut = getSut("property-with-alias-type")
            .properties(includeNested = true)
            .first()

        // then
        with(sut.explicitType) {
            this?.name shouldBeEqualTo "SampleType"
            this?.aliasName shouldBeEqualTo "AliasType"
            this?.isTypeAlias shouldBeEqualTo true
            this?.fullyQualifiedName shouldBeEqualTo "com.lemon.konsist.testdata.SampleType"
        }
    }

    @Test
    fun `property-without-type`() {
        // given
        val sut = getSut("property-without-type")
            .properties(includeNested = true)
            .first()

        // then
        with(sut.explicitType) {
            this?.name shouldBeEqualTo null
            this?.aliasName shouldBeEqualTo null
            this?.isTypeAlias shouldBeEqualTo null
            this?.fullyQualifiedName shouldBeEqualTo null
        }
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koproperty/snippet/$fileName.kttxt")
}
