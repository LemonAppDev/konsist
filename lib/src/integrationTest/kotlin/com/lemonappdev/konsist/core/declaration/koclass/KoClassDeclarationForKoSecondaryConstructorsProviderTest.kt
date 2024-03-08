package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoSecondaryConstructorsProviderTest {
    @Test
    fun `class-has-no-secondary-constructor`() {
        // given
        val sut =
            getSnippetFile("class-has-no-secondary-constructor")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors.isEmpty() shouldBeEqualTo true
            numSecondaryConstructors shouldBeEqualTo 0
            countSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo 0
            hasSecondaryConstructors() shouldBeEqualTo false
            hasSecondaryConstructor { it.hasPublicModifier } shouldBeEqualTo false
            hasAllSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo true
            hasSecondaryConstructors shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-one-secondary-constructor`() {
        // given
        val sut =
            getSnippetFile("class-has-one-secondary-constructor")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors.isNotEmpty() shouldBeEqualTo true
            numSecondaryConstructors shouldBeEqualTo 1
            countSecondaryConstructors { it.hasPrivateModifier } shouldBeEqualTo 1
            countSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo 0
            hasSecondaryConstructors() shouldBeEqualTo true
            hasSecondaryConstructor { it.hasPrivateModifier } shouldBeEqualTo true
            hasSecondaryConstructor { it.hasPublicModifier } shouldBeEqualTo false
            hasAllSecondaryConstructors { it.hasPrivateModifier } shouldBeEqualTo true
            hasAllSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo false
            hasSecondaryConstructors shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-two-secondary-constructors`() {
        // given
        val sut =
            getSnippetFile("class-has-two-secondary-constructors")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors.isNotEmpty() shouldBeEqualTo true
            numSecondaryConstructors shouldBeEqualTo 2
            countSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo 1
            countSecondaryConstructors { it.hasPublicOrDefaultModifier } shouldBeEqualTo 2
            hasSecondaryConstructors() shouldBeEqualTo true
            hasSecondaryConstructor { it.hasPublicModifier } shouldBeEqualTo true
            hasSecondaryConstructor { it.hasPrivateModifier } shouldBeEqualTo false
            hasAllSecondaryConstructors { it.hasPublicOrDefaultModifier } shouldBeEqualTo true
            hasAllSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructor`() {
        // given
        val sut =
            getSnippetFile("class-has-primary-and-secondary-constructor")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            secondaryConstructors shouldHaveSize 1
            numSecondaryConstructors shouldBeEqualTo 1
            countSecondaryConstructors { it.hasPublicOrDefaultModifier } shouldBeEqualTo 1
            countSecondaryConstructors { it.hasPublicModifier } shouldBeEqualTo 0
            hasSecondaryConstructors shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkosecondaryconstructorsprovider/", fileName)
}
