package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleParentClass
import com.lemonappdev.konsist.testdata.SampleParentClass2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LongMethod")
class KoClassDeclarationForKoParentClassProviderTest {
    @Test
    fun `class-has-no-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-has-no-parent-class")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClass shouldBeEqualTo null
            parentClasses() shouldBeEqualTo emptyList()
            numParentClasses() shouldBeEqualTo 0
            countParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo 0
            hasParentClass() shouldBeEqualTo false
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo false
            hasParentClassWithName(emptyList()) shouldBeEqualTo false
            hasParentClassWithName(emptySet()) shouldBeEqualTo false
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo true
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassWithName(listOf("SampleParentClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("SampleParentClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames("SampleParentClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("SampleParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo false
            hasParentClassOf(listOf(SampleParentClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(SampleParentClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(SampleParentClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-only-direct-parent-class`() {
        // given
        val sut =
            getSnippetFile("class-has-only-direct-parent-class")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            parentClasses().map { it.name } shouldBeEqualTo listOf("SampleParentClass")
            numParentClasses() shouldBeEqualTo 1
            countParentClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo 1
            countParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo 0
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo true
            hasParentClassWithName(emptyList()) shouldBeEqualTo true
            hasParentClassWithName(emptySet()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasAllParentClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassWithName(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames("SampleParentClass") shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("SampleParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentClassOf(SampleClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo true
            hasParentClassOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(listOf(SampleClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(SampleClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(SampleParentClass::class) shouldBeEqualTo true
            hasAllParentClassesOf(SampleClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(listOf(SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(setOf(SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-class-interfaces-and-external-parent`() {
        // given
        val sut =
            getSnippetFile("class-has-parent-class-interfaces-and-external-parent")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClass"
            parentClasses().map { it.name } shouldBeEqualTo listOf("SampleParentClass")
            numParentClasses() shouldBeEqualTo 1
            countParentClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo 1
            countParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo 0
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "SampleParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo true
            hasParentClassWithName(emptyList()) shouldBeEqualTo true
            hasParentClassWithName(emptySet()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasAllParentClasses { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("SampleParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassWithName(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames("SampleParentClass") shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("SampleParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class) shouldBeEqualTo true
            hasParentClassOf(SampleClass::class) shouldBeEqualTo false
            hasParentClassOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo true
            hasParentClassOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(listOf(SampleClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(SampleClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(SampleParentClass::class) shouldBeEqualTo true
            hasAllParentClassesOf(SampleClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(SampleParentClass::class, SampleClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(listOf(SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(setOf(SampleClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass::class, SampleClass::class)) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-class-with-duplicated-name`() {
        /*
        In Kotlin, it is possible to have two classes sharing the same name under two conditions: one class is defined
        within the current file, and the other is defined externally, in a separate file. When both classes are referenced
        within the current context, Kotlin's scoping rules prioritize the external (imported) class over the internally
        defined one.
         */

        // given
        val sut =
            getSnippetFile("class-has-parent-class-with-duplicated-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo "SampleParentClassWithDuplicatedName"
//            parentClass?.asClassDeclaration()?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.konsist.testdata.SampleParentClassWithDuplicatedName"
        }
    }

    @Test
    fun `class-has-indirect-parent-classes`() {
        // given
        val sut =
            getSnippetFile("class-has-indirect-parent-classes")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClasses(indirectParents = false).map { it.name } shouldBeEqualTo listOf("SampleParentClass")
            parentClasses(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "SampleParentClass",
                    "SampleParentClass1",
                    "SampleParentClass2",
                )
            numParentClasses(indirectParents = false) shouldBeEqualTo 1
            numParentClasses(indirectParents = true) shouldBeEqualTo 3
            countParentClasses(indirectParents = false) { it.name == "SampleParentClass1" } shouldBeEqualTo 0
            countParentClasses(indirectParents = true) { it.name == "SampleParentClass1" } shouldBeEqualTo 1
            countParentClasses(indirectParents = false) { it.hasNameStartingWith("SampleParent") } shouldBeEqualTo 1
            countParentClasses(indirectParents = true) { it.hasNameStartingWith("SampleParent") } shouldBeEqualTo 3
            hasParentClass() shouldBeEqualTo true
            hasParentClasses(indirectParents = false) shouldBeEqualTo true
            hasParentClasses(indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(emptyList(), indirectParents = false) shouldBeEqualTo true
            hasParentClassWithName(emptySet(), indirectParents = false) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList(), indirectParents = false) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet(), indirectParents = false) shouldBeEqualTo true
            hasParentClassWithName("SampleParentClass1", indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName("OtherClass", indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                "SampleParentClass1",
                "SampleParentClass2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                "SampleParentClass1",
                "OtherClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(listOf("SampleParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                listOf(
                    "SampleParentClass1",
                    "SampleParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                listOf(
                    "SampleParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(setOf("SampleParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                setOf(
                    "SampleParentClass1",
                    "SampleParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                setOf(
                    "SampleParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames("SampleParentClass1", indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass", indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                "SampleParentClass1",
                "SampleParentClass2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                "SampleParentClass1",
                "OtherClass",
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("SampleParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                listOf(
                    "SampleParentClass1",
                    "SampleParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                listOf(
                    "SampleParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("SampleParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                setOf(
                    "SampleParentClass1",
                    "SampleParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                setOf(
                    "SampleParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClass(indirectParents = true) { it.name == "SampleParentClass1" } shouldBeEqualTo true
            hasParentClass(indirectParents = true) { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParentClasses(indirectParents = true) { it.name == "SampleParentClass1" } shouldBeEqualTo false
            hasAllParentClasses(indirectParents = true) { it.hasNameStartingWith("Sample") } shouldBeEqualTo true
            hasAllParentClasses(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentClassOf(SampleParentClass2::class, indirectParents = true) shouldBeEqualTo true
            hasParentClassOf(listOf(SampleParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasParentClassOf(setOf(SampleParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(SampleParentClass2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                SampleParentClass2::class,
                SampleClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(SampleParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                listOf(
                    SampleParentClass2::class,
                    SampleClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(SampleParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                setOf(
                    SampleParentClass2::class,
                    SampleClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoparentclassprovider/", fileName)
}
