package com.lemonappdev.konsist.declaration.kochild

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.children
import com.lemonappdev.konsist.api.ext.list.print
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoChildDeclarationForKoNameProviderTest {
    @Test
    fun `class child name`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentSuperClass")
            .children()
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "ParentClass"
            hasName("ParentClass") shouldBeEqualTo true
            hasName("OtherClass") shouldBeEqualTo false
            hasName("parentclass", ignoreCase = false) shouldBeEqualTo false
            hasName("parentclass", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `object child name`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentClassForObject")
            .children()
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleObject"
            hasName("SampleObject") shouldBeEqualTo true
            hasName("OtherObject") shouldBeEqualTo false
            hasName("sampleobject", ignoreCase = false) shouldBeEqualTo false
            hasName("sampleobject", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface child name`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("ParentSuperInterface")
            .children()
            .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "ParentInterface"
            hasName("ParentInterface") shouldBeEqualTo true
            hasName("OtherInterface") shouldBeEqualTo false
            hasName("parentinterface", ignoreCase = false) shouldBeEqualTo false
            hasName("parentinterface", ignoreCase = true) shouldBeEqualTo true
        }
    }
}
