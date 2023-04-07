package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.testdata.SampleAnnotation
import com.lemon.konsist.testdata.SampleAnnotation1
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForSecondaryConstructorTest {
    @Test
    fun `secondary-constructor-without-visibility-modifiers`() {
        // given
        val sut = getSut("secondary-constructor-without-visibility-modifiers")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
    }

    @Test
    fun `public-secondary-constructor`() {
        // given
        val sut = getSut("public-secondary-constructor")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
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
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo false
        }
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
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo false
            isProtected shouldBeEqualTo true
        }
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
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isInternal shouldBeEqualTo true
            isProtected shouldBeEqualTo false
        }
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
    fun `secondary-constructor-has-annotation`() {
        // given
        val sut = getSut("secondary-constructor-has-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            annotations.map { it.name } shouldBeEqualTo listOf("SampleAnnotation")
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(SampleAnnotation1::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-no-annotation`() {
        // given
        val sut = getSut("secondary-constructor-has-no-annotation")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.run {
            annotations.isEmpty() shouldBeEqualTo true
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("kodeclaration/snippet/forsecondaryconstructor/", fileName)
}
