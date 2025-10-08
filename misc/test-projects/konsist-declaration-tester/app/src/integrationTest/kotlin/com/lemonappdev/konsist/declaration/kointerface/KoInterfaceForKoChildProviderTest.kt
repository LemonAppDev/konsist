package com.lemonappdev.konsist.declaration.kointerface

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.print
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceForKoChildProviderTest {
    @Test
    fun `interface without children`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("InterfaceWithoutChildren")
            .first()

        // then
        assertSoftly(sut) {
            children() shouldBeEqualTo emptyList()
            numChildren() shouldBeEqualTo 0
            countChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo 0
            hasChildren() shouldBeEqualTo false
            hasChildWithName(emptyList()) shouldBeEqualTo false
            hasChildWithName(emptySet()) shouldBeEqualTo false
            hasChildrenWithAllNames(emptyList()) shouldBeEqualTo false
            hasChildrenWithAllNames(emptySet()) shouldBeEqualTo false
            hasChildWithName("ParentInterface") shouldBeEqualTo false
            hasChildWithName(listOf("ParentInterface")) shouldBeEqualTo false
            hasChildWithName(setOf("ParentInterface")) shouldBeEqualTo false
            hasChildrenWithAllNames("ParentInterface") shouldBeEqualTo false
            hasChildrenWithAllNames(listOf("ParentInterface")) shouldBeEqualTo false
            hasChildrenWithAllNames(setOf("ParentInterface")) shouldBeEqualTo false
            hasChild { it.hasNameStartingWith("Parent") } shouldBeEqualTo false
            hasAllChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
        }
    }

    @Test
    fun `interface with direct child`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("ParentSuperInterface")
            .first()

        // then
        assertSoftly(sut) {
            children().map { it.name } shouldBeEqualTo listOf("ParentInterface")
            numChildren() shouldBeEqualTo 1
            countChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo 1
            countChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo 0
            hasChildren() shouldBeEqualTo true
            hasChildWithName(emptyList()) shouldBeEqualTo true
            hasChildWithName(emptySet()) shouldBeEqualTo true
            hasChildrenWithAllNames(emptyList()) shouldBeEqualTo true
            hasChildrenWithAllNames(emptySet()) shouldBeEqualTo true
            hasChildWithName("ParentInterface") shouldBeEqualTo true
            hasChildWithName("ParentInterface", "OtherInterface") shouldBeEqualTo true
            hasChildWithName(listOf("ParentInterface")) shouldBeEqualTo true
            hasChildWithName(listOf("ParentInterface", "OtherInterface")) shouldBeEqualTo true
            hasChildWithName(setOf("ParentInterface")) shouldBeEqualTo true
            hasChildWithName(setOf("ParentInterface", "OtherInterface")) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface") shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "OtherInterface") shouldBeEqualTo false
            hasChildrenWithAllNames(listOf("ParentInterface")) shouldBeEqualTo true
            hasChildrenWithAllNames(listOf("ParentInterface", "OtherInterface")) shouldBeEqualTo false
            hasChildrenWithAllNames(setOf("ParentInterface")) shouldBeEqualTo true
            hasChildrenWithAllNames(setOf("ParentInterface", "OtherInterface")) shouldBeEqualTo false
            hasChild { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasChild { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasAllChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }

    @Test
    fun `interface with indirect children`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("ParentSuperInterface")
            .first()

        // then
        assertSoftly(sut) {
            children(indirectChildren = true).map { it.name } shouldBeEqualTo listOf("AppClass", "ParentInterface")
            numChildren(indirectChildren = true) shouldBeEqualTo 2
            countChildren(indirectChildren = true) { it.hasNameStartingWith("Parent") } shouldBeEqualTo 1
            countChildren(indirectChildren = true) { it.hasNameStartingWith("App") } shouldBeEqualTo 1
            hasChildren(indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(emptyList(), indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(emptySet(), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(emptyList(), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(emptySet(), indirectChildren = true) shouldBeEqualTo true
            hasChildWithName("ParentInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildWithName("ParentInterface", "OtherInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(listOf("ParentInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(listOf("ParentInterface", "OtherInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(setOf("ParentInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildWithName(setOf("ParentInterface", "OtherInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "AppClass", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "OtherInterface", indirectChildren = true) shouldBeEqualTo false
            hasChildrenWithAllNames(listOf("ParentInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(listOf("ParentInterface", "AppClass"), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(listOf("ParentInterface", "OtherInterface"), indirectChildren = true) shouldBeEqualTo false
            hasChildrenWithAllNames(setOf("ParentInterface"), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(setOf("ParentInterface", "AppClass"), indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames(setOf("ParentInterface", "OtherInterface"), indirectChildren = true) shouldBeEqualTo false
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Parent") || it.hasNameStartingWith("App") }
                .shouldBeEqualTo(true)
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }
}
