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
    fun `interface with direct child`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("ParentInterface")
            .first()

        // then
        assertSoftly(sut) {
            children().map { it.name } shouldBeEqualTo listOf("AppInterface")
            numChildren() shouldBeEqualTo 1
            countChildren { it.hasNameStartingWith("App") } shouldBeEqualTo true
            countChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasChildren() shouldBeEqualTo true
            hasChildWithName("AppInterface") shouldBeEqualTo true
            hasChildWithName("AppInterface", "OtherInterface") shouldBeEqualTo true
            hasChildrenWithAllNames("AppInterface") shouldBeEqualTo true
            hasChildrenWithAllNames("AppInterface", "OtherInterface") shouldBeEqualTo false
            hasChild { it.hasNameStartingWith("App") } shouldBeEqualTo true
            hasChild { it.hasNameStartingWith("Other") } shouldBeEqualTo false
            hasAllChildren { it.hasNameStartingWith("App") } shouldBeEqualTo true
            hasAllChildren { it.hasNameStartingWith("Other") } shouldBeEqualTo false
        }
    }
}
