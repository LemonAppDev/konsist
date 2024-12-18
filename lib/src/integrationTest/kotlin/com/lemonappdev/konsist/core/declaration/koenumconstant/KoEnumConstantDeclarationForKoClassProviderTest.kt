package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoClassProviderTest {
    @Test
    fun `enum-constant-contains-no-classes`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-no-classes")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            classes() shouldBeEqualTo emptyList()
            numClasses() shouldBeEqualTo 0
            countClasses { it.name == "SampleInnerClass" } shouldBeEqualTo 0
            hasClasses() shouldBeEqualTo false
            hasClassWithName(emptyList()) shouldBeEqualTo false
            hasClassWithName(emptySet()) shouldBeEqualTo false
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasClassWithName("SampleInnerClass") shouldBeEqualTo false
            hasClassWithName(listOf("SampleInnerClass")) shouldBeEqualTo false
            hasClassWithName(setOf("SampleInnerClass")) shouldBeEqualTo false
            hasClassesWithAllNames("SampleInnerClass1", "SampleInnerClass2") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("SampleInnerClass1", "SampleInnerClass2")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("SampleInnerClass1", "SampleInnerClass2")) shouldBeEqualTo false
            hasClass { it.name == "SampleInnerClass" } shouldBeEqualTo false
            hasAllClasses { it.name == "SampleInnerClass" } shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-constant-contains-class`() {
        // given
        val sut =
            getSnippetFile("enum-constant-contains-class")
                .classes()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            classes().map { it.name } shouldBeEqualTo listOf("SampleInnerClass1", "SampleInnerClass2")
            numClasses() shouldBeEqualTo 2
            countClasses { it.name == "SampleInnerClass1" } shouldBeEqualTo 1
            hasClasses() shouldBeEqualTo true
            hasClassWithName(emptyList()) shouldBeEqualTo true
            hasClassWithName(emptySet()) shouldBeEqualTo true
            hasClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasClassWithName("SampleInnerClass1") shouldBeEqualTo true
            hasClassWithName("OtherClass") shouldBeEqualTo false
            hasClassWithName("SampleInnerClass1", "OtherClass") shouldBeEqualTo true
            hasClassWithName(listOf("SampleInnerClass1")) shouldBeEqualTo true
            hasClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasClassWithName(listOf("SampleInnerClass1", "OtherClass")) shouldBeEqualTo true
            hasClassWithName(setOf("SampleInnerClass1")) shouldBeEqualTo true
            hasClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasClassWithName(setOf("SampleInnerClass1", "OtherClass")) shouldBeEqualTo true
            hasClassesWithAllNames("SampleInnerClass1") shouldBeEqualTo true
            hasClassesWithAllNames("SampleInnerClass1", "SampleInnerClass2") shouldBeEqualTo true
            hasClassesWithAllNames("SampleInnerClass1", "OtherClass") shouldBeEqualTo false
            hasClassesWithAllNames(listOf("SampleInnerClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("SampleInnerClass1", "SampleInnerClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(listOf("SampleInnerClass1", "OtherClass")) shouldBeEqualTo false
            hasClassesWithAllNames(setOf("SampleInnerClass1")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("SampleInnerClass1", "SampleInnerClass2")) shouldBeEqualTo true
            hasClassesWithAllNames(setOf("SampleInnerClass1", "OtherClass")) shouldBeEqualTo false
            hasClass { it.name == "SampleInnerClass1" } shouldBeEqualTo true
            hasClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllClasses { it.name.endsWith("2") || it.name == "SampleInnerClass1" } shouldBeEqualTo true
            hasAllClasses { it.name.endsWith("2") } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koenumconstant/snippet/forkoclassprovider/", fileName)
}
