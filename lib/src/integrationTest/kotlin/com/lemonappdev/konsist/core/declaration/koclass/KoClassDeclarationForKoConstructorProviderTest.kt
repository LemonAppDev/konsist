package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoConstructorProviderTest {
    @Test
    fun `class-has-no-constructors`() {
        // given
        val sut =
            getSnippetFile("class-has-no-constructors")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            constructors shouldBeEqualTo emptyList()
            numConstructors shouldBeEqualTo 0
            countConstructors { it.hasModifiers() } shouldBeEqualTo 0
            hasConstructors() shouldBeEqualTo false
            hasConstructor { it.hasModifiers() } shouldBeEqualTo false
            hasAllConstructors { it.hasModifiers() } shouldBeEqualTo true
        }
    }

    @Test
    fun `class-has-primary-and-secondary-constructors`() {
        // given
        val sut =
            getSnippetFile("class-has-primary-and-secondary-constructors")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            constructors shouldNotBeEqualTo emptyList()
            numConstructors shouldBeEqualTo 3
            countConstructors { it.hasPublicOrDefaultModifier } shouldBeEqualTo 3
            countConstructors { it.hasPublicModifier } shouldBeEqualTo 1
            hasConstructors() shouldBeEqualTo true
            hasConstructor { it.hasPublicModifier } shouldBeEqualTo true
            hasConstructor { it.hasPrivateModifier } shouldBeEqualTo false
            hasAllConstructors { it.hasParameterNamed("sampleProperty1") } shouldBeEqualTo true
            hasAllConstructors { it.hasPublicModifier } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoconstructorsprovider/", fileName)
}
