package com.lemonappdev.konsist.core.declaration.koexplicitbackingfield

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExplicitBackingFieldDeclarationForKoTacitTypeProviderTest {
    @Test
    fun `explicit-backing-field-has-tacit-type-from-constructor`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-tacit-type-from-constructor")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.hasTacitType("ArrayList") shouldBeEqualTo true
            it?.hasTacitType("List") shouldBeEqualTo false
            it?.hasTacitTypeOf(ArrayList::class) shouldBeEqualTo true
        }
    }

    @Test
    fun `explicit-backing-field-has-tacit-type-from-type`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-tacit-type-from-type")
                .properties()
                .first()
                .explicitBackingField

        // then
        assertSoftly(sut) {
            it?.tacitType?.name shouldBeEqualTo "MutableList<Int>"
            it?.hasTacitType("MutableList") shouldBeEqualTo true
        }
    }

    @Test
    fun `explicit-backing-field-has-no-tacit-type`() {
        // given
        val sut =
            getSnippetFile("explicit-backing-field-has-no-tacit-type")
                .properties()
                .first()
                .explicitBackingField

        // then
        sut?.tacitType shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koexplicitbackingfield/snippet/forkotacittypeprovider/", fileName)
}
