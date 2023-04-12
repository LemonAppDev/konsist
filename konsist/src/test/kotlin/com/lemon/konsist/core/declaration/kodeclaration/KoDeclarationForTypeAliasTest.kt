package com.lemon.konsist.core.declaration.kodeclaration

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.const.KoModifier.PRIVATE
import com.lemon.konsist.testdata.NonExistingAnnotation
import com.lemon.konsist.testdata.SampleAnnotation
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoDeclarationForTypeAliasTest {
    @Test
    fun `typealias-is-top-level`() {
        // given
        val sut = getSut("typealias-is-top-level")
            .typeAliases()
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-annotation`() {
        // given
        val sut = getSut("typealias-has-annotation")
            .typeAliases()
            .first()

        // then
        sut.run {
            annotations shouldHaveSize 1
            hasAnnotation(SampleAnnotation::class) shouldBeEqualTo true
            hasAnnotation(NonExistingAnnotation::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-no-visibility-modifier`() {
        // given
        val sut = getSut("typealias-has-no-visibility-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-public-visibility-modifier`() {
        // given
        val sut = getSut("typealias-has-public-visibility-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo true
            isPublic shouldBeEqualTo true
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-private-visibility-modifier`() {
        // given
        val sut = getSut("typealias-has-private-visibility-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo true
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-protected-visibility-modifier`() {
        // given
        val sut = getSut("typealias-has-protected-visibility-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo true
            isInternal shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-internal-visibility-modifier`() {
        // given
        val sut = getSut("typealias-has-internal-visibility-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            isPublicOrDefault shouldBeEqualTo false
            isPublic shouldBeEqualTo false
            isPrivate shouldBeEqualTo false
            isProtected shouldBeEqualTo false
            isInternal shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-has-fully-qualified-name`() {
        // given
        val sut = getSut("typealias-has-fully-qualified-name")
            .typeAliases()
            .first()

        // then
        sut.fullyQualifiedName shouldBeEqualTo "com.samplepackage.SampleTypeAlias"
    }

    @Test
    fun `typealias-is-in-package`() {
        // given
        val sut = getSut("typealias-is-in-package")
            .typeAliases()
            .first()

        // then
        sut.packageName shouldBeEqualTo "com.samplepackage"
    }

    @Test
    fun `typealias-is-not-in-package`() {
        // given
        val sut = getSut("typealias-is-not-in-package")
            .typeAliases()
            .first()

        // then
        sut.packageName shouldBeEqualTo ""
    }

    @Test
    fun `typealias-has-protected-modifier`() {
        // given
        val sut = getSut("typealias-has-protected-modifier")
            .typeAliases()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSut("typealias-has-public-modifier")
            .typeAliases()
            .first()

        // then
        sut.run {
            hasModifiers(KoModifier.PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-no-modifiers`() {
        // given
        val sut = getSut("typealias-has-no-modifiers")
            .typeAliases()
            .first()

        // then
        sut.run {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-reside-in-path`() {
        // given
        val sut = getSut("typealias-reside-in-path")
            .typeAliases()
            .first()

        // then
        sut.run {
            resideInPath("Test/") shouldBeEqualTo true
            resideInPath("TEST/") shouldBeEqualTo true
            resideInPath("TEST/", false) shouldBeEqualTo false
            resideInPath("Main/") shouldBeEqualTo false
            resideInPath("") shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-reside-outside-path`() {
        // given
        val sut = getSut("typealias-reside-outside-path")
            .typeAliases()
            .first()

        // then
        sut.run {
            resideOutsidePath("Main/") shouldBeEqualTo true
            resideOutsidePath("MAIN/") shouldBeEqualTo true
            resideOutsidePath("MAIN/", false) shouldBeEqualTo true
            resideOutsidePath("Test/") shouldBeEqualTo false
            resideOutsidePath("TEST/") shouldBeEqualTo false
            resideOutsidePath("TEST/", false) shouldBeEqualTo true
            resideOutsidePath("") shouldBeEqualTo false
        }
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/declaration/kodeclaration/snippet/fortypealias/", fileName)
}
