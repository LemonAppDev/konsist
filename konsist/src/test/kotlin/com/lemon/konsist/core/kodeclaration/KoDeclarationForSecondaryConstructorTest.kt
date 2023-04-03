package com.lemon.konsist.core.kodeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForSecondaryConstructorTest {
    @Test
    fun `public-secondary-constructor`() {
        // given
        val sut = getSut("public-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.isPublic shouldBeEqualTo true
    }

    @Test
    fun `private-secondary-constructor`() {
        // given
        val sut = getSut("private-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.isPrivate shouldBeEqualTo true
    }

    @Test
    fun `protected-secondary-constructor`() {
        // given
        val sut = getSut("protected-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.isProtected shouldBeEqualTo true
    }

    @Test
    fun `internal-secondary-constructor`() {
        // given
        val sut = getSut("internal-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.isInternal shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor`() {
        // given
        val sut = getSut("secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.name shouldBeEqualTo "SampleClass"
    }

    @Test
    fun `secondary-constructor-with-annotation`() {
        // given
        val sut = getSut("secondary-constructor-with-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        with(sut) {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-without-annotation`() {
        // given
        val sut = getSut("secondary-constructor-without-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        with(sut) {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/kodeclaration/snippet/forsecondaryconstructor/$fileName.kttxt")
}
