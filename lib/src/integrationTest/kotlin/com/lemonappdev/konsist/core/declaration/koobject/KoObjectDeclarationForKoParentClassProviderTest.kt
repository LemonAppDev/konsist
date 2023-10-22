package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleParentClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoParentClassProviderTest {
    @Test
    fun `object-has-no-parent-class`() {
        // given
        val sut = getSnippetFile("object-has-no-parent-class")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass shouldBeEqualTo null
            hasParentClass() shouldBeEqualTo false
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-only-parent-class`() {
        // given
        val sut = getSnippetFile("object-has-only-parent-class")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentClassOf(SampleClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-interfaces-and-external-parent`() {
        // given
        val sut = getSnippetFile("object-has-parent-class-interfaces-and-external-parent")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentClassOf(SampleClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-parent-class-with-duplicated-name`() {
        /*
        In Kotlin, we may have a situation that we have two classes with the same name - one defined in current file
        and second one defined in another file.

        When we use class with this name as a parent, the correct class is the imported one.
         */
        // given
        val sut = getSnippetFile("object-has-parent-class-with-one-parameter")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClassWithDuplicatedName"
            parentClass?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClassWithDuplicatedName"
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoparentclassprovider/", fileName)
}
