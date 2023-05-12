package com.lemonappdev.konsist.core.declaration.kodeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForModifierTest {
    @Test
    fun `class-has-modifiers`() {
        // given
        val sut = getSnippetFile("class-has-modifiers")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("class-has-modifiers-and-annotations")
            .classes()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, OPEN)
    }

    @Test
    fun `class-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("class-has-protected-modifier")
            .classes()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `class-has-public-modifier`() {
        // given
        val sut = getSnippetFile("class-has-public-modifier")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("class-has-two-modifiers")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-modifier`() {
        // given
        val sut = getSnippetFile("class-has-no-modifier")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN, KoModifier.SUSPEND, KoModifier.INLINE, KoModifier.OPERATOR)
    }

    @Test
    fun `function-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotation-with-parameter")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotation-without-parameter")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("function-has-modifiers-and-annotations")
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("function-has-protected-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `function-has-public-modifier`() {
        // given
        val sut = getSnippetFile("function-has-public-modifier")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-two-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(KoModifier.SUSPEND) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, KoModifier.SUSPEND) shouldBeEqualTo true
            hasModifiers(KoModifier.SUSPEND, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, KoModifier.SUSPEND) shouldBeEqualTo false
            hasModifiers(PROTECTED, KoModifier.SUSPEND, PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-no-modifier`() {
        // given
        val sut = getSnippetFile("function-has-no-modifier")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, KoModifier.ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotation-with-parameter")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, KoModifier.ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotation-without-parameter")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, KoModifier.ABSTRACT)
    }

    @Test
    fun `interface-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("interface-has-modifiers-and-annotations")
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PUBLIC, KoModifier.ABSTRACT)
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-modifier")
            .interfaces()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-two-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(KoModifier.ABSTRACT) shouldBeEqualTo true
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(KoModifier.ABSTRACT, PUBLIC) shouldBeEqualTo true
            hasModifiers(PUBLIC, KoModifier.ABSTRACT) shouldBeEqualTo true
            hasModifiers(PRIVATE, KoModifier.ABSTRACT) shouldBeEqualTo false
            hasModifiers(KoModifier.ABSTRACT, PUBLIC, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-no-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-modifiers")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, KoModifier.DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotation-with-parameter")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, KoModifier.DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotation-without-parameter")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, KoModifier.DATA)
    }

    @Test
    fun `object-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("object-has-modifiers-and-annotations")
            .objects()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE, KoModifier.DATA)
    }

    @Test
    fun `object-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("object-has-protected-modifier")
            .objects()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `object-has-public-modifier`() {
        // given
        val sut = getSnippetFile("object-has-public-modifier")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-two-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(KoModifier.DATA) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PRIVATE, KoModifier.DATA) shouldBeEqualTo true
            hasModifiers(KoModifier.DATA, PRIVATE) shouldBeEqualTo true
            hasModifiers(PROTECTED, KoModifier.DATA) shouldBeEqualTo false
            hasModifiers(PROTECTED, KoModifier.DATA, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `object-has-no-modifier`() {
        // given
        val sut = getSnippetFile("object-has-no-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotation-with-parameter")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotation-without-parameter")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("property-has-modifiers-and-annotations")
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PROTECTED, OPEN)
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("property-has-protected-modifier")
            .properties()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-modifier")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-two-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-two-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN, PRIVATE) shouldBeEqualTo false
            hasModifiers() shouldBeEqualTo true
        }
    }

    @Test
    fun `property-has-no-modifier`() {
        // given
        val sut = getSnippetFile("property-has-no-modifier")
            .properties()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-modifier")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotation-with-parameter")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotation-without-parameter")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("typealias-has-modifiers-and-annotations")
            .typeAliases()
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `typealias-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-protected-modifier")
            .typeAliases()
            .first()

        // then
        sut.hasModifiers() shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-public-modifier")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-has-no-modifiers")
            .typeAliases()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo false
            hasModifiers(PRIVATE) shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-modifiers-and-annotations")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotation-with-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotation-with-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotation-without-parameter`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotation-without-parameter")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-modifiers-and-annotations`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-modifiers-and-annotations")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers shouldBeEqualTo listOf(PRIVATE)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kodeclaration/snippet/formodifier/", fileName)
}
