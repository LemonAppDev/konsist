package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoLocalClassProviderTest {
    @Test
    fun `setter-contains-no-local-classes`() {
        // given
        val sut =
            getSnippetFile("setter-contains-no-local-classes")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localClasses shouldBeEqualTo emptyList()
            it?.numLocalClasses shouldBeEqualTo 0
            it?.countLocalClasses { localClass -> localClass.name == "SampleClass" } shouldBeEqualTo 0
            it?.hasLocalClasses() shouldBeEqualTo false
            it?.hasLocalClassWithName("SampleClass") shouldBeEqualTo false
            it?.hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo false
            it?.hasLocalClass { localClass -> localClass.name == "SampleClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { localClass -> localClass.name == "SampleClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-contains-local-class`() {
        // given
        val sut =
            getSnippetFile("setter-contains-local-class")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.localClasses?.map { localClass -> localClass.name } shouldBeEqualTo
                listOf(
                    "SampleClass1",
                    "SampleClass2",
                )
            it?.numLocalClasses shouldBeEqualTo 2
            it?.countLocalClasses { localClass -> localClass.name == "SampleClass1" } shouldBeEqualTo 1
            it?.hasLocalClasses() shouldBeEqualTo true
            it?.hasLocalClassWithName("SampleClass1") shouldBeEqualTo true
            it?.hasLocalClassWithName("OtherClass") shouldBeEqualTo false
            it?.hasLocalClassWithName("SampleClass1", "OtherClass") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1", "SampleClass2") shouldBeEqualTo true
            it?.hasLocalClassesWithAllNames("SampleClass1", "OtherClass") shouldBeEqualTo false
            it?.hasLocalClass { localClass -> localClass.name == "SampleClass1" } shouldBeEqualTo true
            it?.hasLocalClass { localClass -> localClass.name == "OtherClass" } shouldBeEqualTo false
            it?.hasAllLocalClasses { localClass ->
                localClass.name.endsWith("2") || localClass.name == "SampleClass1"
            }.shouldBeEqualTo(true)
            it?.hasAllLocalClasses { localClass -> localClass.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkolocalclassprovider/", fileName)
}
