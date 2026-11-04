package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.withParentClassOf
import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import com.lemonappdev.konsist.testdata.FixtureClass
import com.lemonappdev.konsist.testdata.FixtureParentClass
import com.lemonappdev.konsist.testdata.FixtureParentClass2
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

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
            hasParentClass { it.name == "FixtureParentClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo false
            hasParentClassWithName(emptyList()) shouldBeEqualTo false
            hasParentClassWithName(emptySet()) shouldBeEqualTo false
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo false
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo false
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo true
            hasParentClassWithName("FixtureParentClass") shouldBeEqualTo false
            hasParentClassWithName("FixtureParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassWithName(listOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames("FixtureParentClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("FixtureParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo false
            hasParentClassOf(listOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(FixtureParentClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
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
            parentClass?.name shouldBeEqualTo "FixtureParentClass"
            parentClasses().map { it.name } shouldBeEqualTo listOf("FixtureParentClass")
            numParentClasses() shouldBeEqualTo 1
            countParentClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo 1
            countParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo 0
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "FixtureParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo true
            hasParentClassWithName(emptyList()) shouldBeEqualTo true
            hasParentClassWithName(emptySet()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasAllParentClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo
                false
            hasParentClassWithName("FixtureParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("FixtureParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassWithName(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames("FixtureParentClass") shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("FixtureParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class) shouldBeEqualTo true
            hasParentClassOf(FixtureClass::class) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo true
            hasParentClassOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(listOf(FixtureClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(FixtureClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(FixtureParentClass::class) shouldBeEqualTo true
            hasAllParentClassesOf(FixtureClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(listOf(FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(setOf(FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
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
            parentClass?.name shouldBeEqualTo "FixtureParentClass"
            parentClasses().map { it.name } shouldBeEqualTo listOf("FixtureParentClass")
            numParentClasses() shouldBeEqualTo 1
            countParentClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo 1
            countParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo 0
            hasParentClass() shouldBeEqualTo true
            hasParentClass { it.name == "FixtureParentClass" } shouldBeEqualTo true
            hasParentClass { it.name == "OtherClass" } shouldBeEqualTo false
            hasParentClasses() shouldBeEqualTo true
            hasParentClassWithName(emptyList()) shouldBeEqualTo true
            hasParentClassWithName(emptySet()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList()) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet()) shouldBeEqualTo true
            hasAllParentClasses { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentClasses { (it.sourceDeclaration as? KoVisibilityModifierProvider)?.hasPrivateModifier == true } shouldBeEqualTo
                false
            hasParentClassWithName("FixtureParentClass") shouldBeEqualTo true
            hasParentClassWithName("OtherClass") shouldBeEqualTo false
            hasParentClassWithName("FixtureParentClass", "OtherClass") shouldBeEqualTo true
            hasParentClassWithName(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames("FixtureParentClass") shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames("FixtureParentClass", "OtherClass") shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass")) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass", "OtherClass")) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class) shouldBeEqualTo true
            hasParentClassOf(FixtureClass::class) shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo true
            hasParentClassOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(listOf(FixtureClass::class)) shouldBeEqualTo false
            hasParentClassOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasParentClassOf(setOf(FixtureClass::class)) shouldBeEqualTo false
            hasParentClassOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(FixtureParentClass::class) shouldBeEqualTo true
            hasAllParentClassesOf(FixtureClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(FixtureParentClass::class, FixtureClass::class) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(listOf(FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class)) shouldBeEqualTo true
            hasAllParentClassesOf(setOf(FixtureClass::class)) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass::class, FixtureClass::class)) shouldBeEqualTo false
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
            parentClass?.name shouldBeEqualTo "FixtureParentClassWithDuplicatedName"
            parentClass
                ?.sourceDeclaration
                ?.asClassDeclaration()
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.konsist.testdata.FixtureParentClassWithDuplicatedName")
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
            parentClasses(indirectParents = false).map { it.name } shouldBeEqualTo listOf("FixtureParentClass")
            parentClasses(indirectParents = true).map { it.name } shouldBeEqualTo
                listOf(
                    "FixtureParentClass",
                    "FixtureParentClass1",
                    "FixtureParentClass2",
                )
            numParentClasses(indirectParents = false) shouldBeEqualTo 1
            numParentClasses(indirectParents = true) shouldBeEqualTo 3
            countParentClasses(indirectParents = false) { it.name == "FixtureParentClass1" } shouldBeEqualTo 0
            countParentClasses(indirectParents = true) { it.name == "FixtureParentClass1" } shouldBeEqualTo 1
            countParentClasses(indirectParents = false) { it.hasNameStartingWith("FixtureParent") } shouldBeEqualTo 1
            countParentClasses(indirectParents = true) { it.hasNameStartingWith("FixtureParent") } shouldBeEqualTo 3
            hasParentClass() shouldBeEqualTo true
            hasParentClasses(indirectParents = false) shouldBeEqualTo true
            hasParentClasses(indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(emptyList(), indirectParents = false) shouldBeEqualTo true
            hasParentClassWithName(emptySet(), indirectParents = false) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptyList(), indirectParents = false) shouldBeEqualTo true
            hasParentClassesWithAllNames(emptySet(), indirectParents = false) shouldBeEqualTo true
            hasParentClassWithName("FixtureParentClass1", indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName("OtherClass", indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                "FixtureParentClass1",
                "FixtureParentClass2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                "FixtureParentClass1",
                "OtherClass",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(listOf("FixtureParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(listOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                listOf(
                    "FixtureParentClass1",
                    "FixtureParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                listOf(
                    "FixtureParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(setOf("FixtureParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassWithName(setOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassWithName(
                setOf(
                    "FixtureParentClass1",
                    "FixtureParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassWithName(
                setOf(
                    "FixtureParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames("FixtureParentClass1", indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames("OtherClass", indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                "FixtureParentClass1",
                "FixtureParentClass2",
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                "FixtureParentClass1",
                "OtherClass",
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("FixtureParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames(listOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                listOf(
                    "FixtureParentClass1",
                    "FixtureParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                listOf(
                    "FixtureParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("FixtureParentClass1"), indirectParents = true) shouldBeEqualTo true
            hasParentClassesWithAllNames(setOf("OtherClass"), indirectParents = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(
                setOf(
                    "FixtureParentClass1",
                    "FixtureParentClass2",
                ),
                indirectParents = true,
            ) shouldBeEqualTo true
            hasParentClassesWithAllNames(
                setOf(
                    "FixtureParentClass1",
                    "OtherClass",
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasParentClass(indirectParents = true) { it.name == "FixtureParentClass1" } shouldBeEqualTo true
            hasParentClass(indirectParents = true) { it.name == "OtherClass" } shouldBeEqualTo false
            hasAllParentClasses(indirectParents = true) { it.name == "FixtureParentClass1" } shouldBeEqualTo false
            hasAllParentClasses(indirectParents = true) { it.hasNameStartingWith("Fixture") } shouldBeEqualTo true
            hasAllParentClasses(indirectParents = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasParentClassOf(FixtureParentClass2::class, indirectParents = true) shouldBeEqualTo true
            hasParentClassOf(listOf(FixtureParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasParentClassOf(setOf(FixtureParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(FixtureParentClass2::class, indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                FixtureParentClass2::class,
                FixtureClass::class,
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentClassesOf(listOf(FixtureParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                listOf(
                    FixtureParentClass2::class,
                    FixtureClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
            hasAllParentClassesOf(setOf(FixtureParentClass2::class), indirectParents = true) shouldBeEqualTo true
            hasAllParentClassesOf(
                setOf(
                    FixtureParentClass2::class,
                    FixtureClass::class,
                ),
                indirectParents = true,
            ) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-no-parent-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-no-parent-class-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasParentClassWithName("fixtureparentclass") shouldBeEqualTo false
            hasParentClassWithName("fixtureparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentClassWithName(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentClassWithName(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentClassWithName(setOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentClassWithName(setOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentClassesWithAllNames("fixtureparentclass", "fixtureparentinterface1") shouldBeEqualTo false
            hasParentClassesWithAllNames("fixtureparentclass", "fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentClassesWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentClassesWithAllNames(setOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-has-parent-class-ignore-case`() {
        // given
        val sut =
            getSnippetFile("class-has-parent-class-ignore-case")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            hasParentWithName("fixtureparentclass") shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName("otherparentclass") shouldBeEqualTo false
            hasParentWithName("otherparentclass", ignoreCase = true) shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", "otherName") shouldBeEqualTo false
            hasParentWithName("fixtureparentclass", "otherName", ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo true
            hasParentWithName(listOf("otherparentclass")) shouldBeEqualTo false
            hasParentWithName(listOf("otherparentclass"), ignoreCase = true) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass", "otherName")) shouldBeEqualTo false
            hasParentWithName(listOf("fixtureparentclass", "otherName"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentclass") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1") shouldBeEqualTo false
            hasParentsWithAllNames("fixtureparentclass", "fixtureparentinterface1", ignoreCase = true) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass"), ignoreCase = true) shouldBeEqualTo true
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1")) shouldBeEqualTo false
            hasParentsWithAllNames(listOf("fixtureparentclass", "fixtureparentinterface1"), ignoreCase = true) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForParentDeclaredOutsideProject")
    fun `parent-declared-outside-project`(
        fileName: String,
        parentClassName: String?,
        parentInterfaceNames: List<String>,
    ) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo parentClassName
            parentClasses().map { it.name } shouldBeEqualTo listOfNotNull(parentClassName)
            hasParentClass() shouldBeEqualTo (parentClassName != null)
            parentInterfaces().map { it.name } shouldBeEqualTo parentInterfaceNames
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForParentNotOnClasspath")
    fun `parent-not-on-classpath`(
        className: String,
        parentClassName: String?,
        parentInterfaceNames: List<String>,
    ) {
        // given
        val sut =
            Konsist
                .scopeFromFile(UNCOMPILED_PARENT_TEST_DATA_PATH)
                .classes()
                .first { it.name == className }

        // then
        assertSoftly(sut) {
            parentClass?.name shouldBeEqualTo parentClassName
            parentClasses().map { it.name } shouldBeEqualTo listOfNotNull(parentClassName)
            parentInterfaces().map { it.name } shouldBeEqualTo parentInterfaceNames
        }
    }

    @Test
    fun `class-has-kotlin-type-parent-class`() {
        // given
        val sut = getSnippetFile("class-has-kotlin-type-parent-class").classes()

        // then
        assertSoftly(sut) {
            first().hasParentClassOf(Throwable::class) shouldBeEqualTo true
            withParentClassOf(Throwable::class).map { it.name } shouldBeEqualTo listOf("FixtureClass")
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkoparentclassprovider/", fileName)

    companion object {
        private const val UNCOMPILED_PARENT_TEST_DATA_PATH =
            "lib/src/integrationTest/resources/com/lemonappdev/konsist/testdata/UncompiledParentTestData.kt"

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParentDeclaredOutsideProject() =
            listOf(
                arguments("class-has-external-parent-class", "FixtureExternalClass", listOf("FixtureExternalInterface")),
                arguments("class-has-kotlin-type-parent-class", "Throwable", emptyList<String>()),
                arguments("class-has-typealias-parent-class", "FixtureTypeAlias", emptyList<String>()),
                arguments("class-without-primary-constructor-has-kotlin-type-parent-class", "Exception", emptyList<String>()),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForParentNotOnClasspath() =
            listOf(
                arguments("FixtureClassWithParentClassNotOnClasspath", "UnknownClass", listOf("UnknownInterface")),
                arguments("FixtureClassWithDelegatedParentInterfaceNotOnClasspath", null, listOf("UnknownInterface")),
                arguments("FixtureClassWithoutPrimaryConstructorWithParentClassNotOnClasspath", null, emptyList<String>()),
            )
    }
}
