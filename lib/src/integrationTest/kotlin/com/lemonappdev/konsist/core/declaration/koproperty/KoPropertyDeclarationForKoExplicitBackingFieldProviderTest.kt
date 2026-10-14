package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.properties
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoExplicitBackingFieldProviderTest {
    @Test
    fun `property-has-no-explicit-backing-field`() {
        // given
        val sut =
            getSnippetFile("property-has-no-explicit-backing-field")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            explicitBackingField shouldBeEqualTo null
            hasExplicitBackingField shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-explicit-backing-field`() {
        // given
        val sut =
            getSnippetFile("property-has-explicit-backing-field")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            explicitBackingField shouldNotBeEqualTo null
            hasExplicitBackingField shouldBeEqualTo true
        }
    }

    @Test
    fun `property-in-class-has-explicit-backing-field`() {
        // given
        val sut =
            getSnippetFile("property-in-class-has-explicit-backing-field")
                .classes()
                .first()
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            explicitBackingField?.hasTypeOf(MutableList::class) shouldBeEqualTo true
            hasExplicitBackingField shouldBeEqualTo true
        }
    }

    @Test
    fun `property-defined-in-constructor-has-no-explicit-backing-field`() {
        // given
        val sut =
            getSnippetFile("property-defined-in-constructor-has-no-explicit-backing-field")
                .classes()
                .first()
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            explicitBackingField shouldBeEqualTo null
            hasExplicitBackingField shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koproperty/snippet/forkoexplicitbackingfieldprovider/", fileName)
}
