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
            hasChildWithName("ParentInterface") shouldBeEqualTo false
            hasChildrenWithAllNames("ParentInterface") shouldBeEqualTo false
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
            hasChildWithName("ParentInterface") shouldBeEqualTo true
            hasChildWithName("ParentInterface", "OtherInterface") shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface") shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "OtherInterface") shouldBeEqualTo false
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
            hasChildWithName("ParentInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildWithName("ParentInterface", "OtherInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "AppClass", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentInterface", "OtherInterface", indirectChildren = true) shouldBeEqualTo false
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Parent") || it.hasNameStartingWith("App") }
                .shouldBeEqualTo(true)
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }
}
