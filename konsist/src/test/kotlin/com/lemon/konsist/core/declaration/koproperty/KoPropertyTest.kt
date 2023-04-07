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
        }
    }

    @Test
    fun `property-has-lateinit-modifier`() {
        // given
        val sut = getSut("property-has-lateinit-modifier")
            .properties()
            .first()

        // then
        sut.isLateinit shouldBeEqualTo true
    }

    @Test
    fun `property-has-override-modifier`() {
        // given
        val sut = getSut("property-has-override-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.isOverride shouldBeEqualTo true
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut = getSut("property-has-abstract-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.isAbstract shouldBeEqualTo true
    }

    @Test
    fun `property-has-open-modifier`() {
        // given
        val sut = getSut("property-has-open-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.isOpen shouldBeEqualTo true
    }

    @Test
    fun `property-has-final-modifier`() {
        // given
        val sut = getSut("property-has-final-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.isFinal shouldBeEqualTo true
    }

    @Test
    fun `property-is-const`() {
        // given
        val sut = getSut("property-is-const")
            .properties(includeNested = true)
            .first()

        // then
        sut.isConst shouldBeEqualTo true
    }

    @Test
    fun `property-has-no-modifiers`() {
        // given
        val sut = getSut("property-has-no-modifiers")
            .properties()
            .first()

        // then
        with(sut) {
            isLateinit shouldBeEqualTo false
            isOverride shouldBeEqualTo false
            isAbstract shouldBeEqualTo false
            isOpen shouldBeEqualTo false
            isFinal shouldBeEqualTo false
            isConst shouldBeEqualTo false
        }
    }

    @Test
    fun `property-is-extension`() {
        // given
        val sut = getSut("property-is-extension")
            .properties()
            .first()

        // then
        sut.isExtension shouldBeEqualTo true
    }

    @Test
    fun `property-is-not-extension`() {
        // given
        val sut = getSut("property-is-not-extension")
            .properties()
            .first()

        // then
        sut.isExtension shouldBeEqualTo false
    }

    @Test
    fun `property-has-simple-type`() {
        // given
        val sut = getSut("property-has-simple-type")
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
    fun `property-has-alias-type`() {
        // given
        val sut = getSut("property-has-alias-type")
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
    fun `property-has-no-type`() {
        // given
        val sut = getSut("property-has-no-type")
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

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("koproperty/snippet/", fileName)
}
