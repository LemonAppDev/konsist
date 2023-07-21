package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForParentTest {
    @Test
    fun `class-has-parent-class-and-interfaces`() {
        // given
        val sut = getSnippetFile("class-has-parent-class-and-interfaces")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentDeclarations.map { it.name }.toList() shouldBeEqualTo listOf(
                "SampleParentClass",
                "SampleParentInterface1",
                "SampleParentInterface2"
            )
            hasParentDeclarations() shouldBeEqualTo true
            hasParentDeclarations("SampleParentClass") shouldBeEqualTo true
            hasParentDeclarations("OtherInterface") shouldBeEqualTo false
            hasParentDeclarations("SampleParentClass", "SampleParentInterface1") shouldBeEqualTo true
            hasParentDeclarations("SampleParentClass", "SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            hasParentClass() shouldBeEqualTo true
            hasParentClass("SampleParentClass") shouldBeEqualTo true
            hasParentClass("OtherClass") shouldBeEqualTo false
            parentInterfaces.map { it.name }.toList() shouldBeEqualTo listOf("SampleParentInterface1", "SampleParentInterface2")
            hasParentInterfaces() shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1") shouldBeEqualTo true
            hasParentInterfaces("OtherInterface") shouldBeEqualTo false
            hasParentInterfaces("SampleParentInterface1", "SampleParentInterface2") shouldBeEqualTo true
            hasParentInterfaces("SampleParentInterface1", "OtherInterface") shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-parents`() {
        // given
        val sut = getSnippetFile("class-has-no-parents")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            parentDeclarations.toList() shouldBeEqualTo emptyList()
            hasParentDeclarations() shouldBeEqualTo false
            hasParentDeclarations("SampleClass") shouldBeEqualTo false
            parentClass shouldBeEqualTo null
            hasParentClass() shouldBeEqualTo false
            hasParentClass("SampleParentClass") shouldBeEqualTo false
            parentInterfaces.toList() shouldBeEqualTo emptyList()
            hasParentInterfaces() shouldBeEqualTo false
            hasParentInterfaces("SampleInterface") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forparent/", fileName)
}
