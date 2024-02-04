package com.lemonappdev.konsist.declaration.koclass

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.print
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassForKoChildProviderTest {
    @Test
    fun `class without children`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("AppClass")
            .first()

        // then
        assertSoftly(sut) {
            children() shouldBeEqualTo emptyList()
            numChildren() shouldBeEqualTo 0
            countChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo 0
            hasChildren() shouldBeEqualTo false
            hasChildWithName("ParentClass") shouldBeEqualTo false
            hasChildrenWithAllNames("ParentClass") shouldBeEqualTo false
            hasChild { it.hasNameStartingWith("Parent") } shouldBeEqualTo false
            hasAllChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
        }
    }

    @Test
    fun `class with direct child`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentSuperClass")
            .first()

        // then
        assertSoftly(sut) {
            children().map { it.name } shouldBeEqualTo listOf("ParentClass")
            numChildren() shouldBeEqualTo 1
            countChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo 1
            countChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo 0
            hasChildren() shouldBeEqualTo true
            hasChildWithName("ParentClass") shouldBeEqualTo true
            hasChildWithName("ParentClass", "OtherClass") shouldBeEqualTo true
            hasChildrenWithAllNames("ParentClass") shouldBeEqualTo true
            hasChildrenWithAllNames("ParentClass", "OtherClass") shouldBeEqualTo false
            hasChild { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasChild { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasAllChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }

    @Test
    fun `class with indirect children`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentSuperClass")
            .first()

        // then
        assertSoftly(sut) {
            children(indirectChildren = true).map { it.name } shouldBeEqualTo listOf("AppClass", "ParentClass")
            numChildren(indirectChildren = true) shouldBeEqualTo 2
            countChildren(indirectChildren = true) { it.hasNameStartingWith("Parent") } shouldBeEqualTo 1
            countChildren(indirectChildren = true) { it.hasNameStartingWith("App") } shouldBeEqualTo 1
            hasChildren(indirectChildren = true) shouldBeEqualTo true
            hasChildWithName("ParentClass", indirectChildren = true) shouldBeEqualTo true
            hasChildWithName("ParentClass", "OtherClass", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentClass", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentClass", "AppClass", indirectChildren = true) shouldBeEqualTo true
            hasChildrenWithAllNames("ParentClass", "OtherClass", indirectChildren = true) shouldBeEqualTo false
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Parent") } shouldBeEqualTo true
            hasChild(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Parent") || it.hasNameStartingWith("App") }
                .shouldBeEqualTo(true)
            hasAllChildren(indirectChildren = true) { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }
}
