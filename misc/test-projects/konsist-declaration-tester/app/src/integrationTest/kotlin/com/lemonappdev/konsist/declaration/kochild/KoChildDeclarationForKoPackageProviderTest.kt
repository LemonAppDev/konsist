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

class KoChildDeclarationForKoPackageProviderTest {
    @Test
    fun `class child packagee`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentSuperClass")
            .children()
            .first()

        // then
            sut.packagee?.name shouldBeEqualTo "com.lemonappdev.sample"
    }

    @Test
    fun `object child packagee`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .classes()
            .withName("ParentClassForObject")
            .children()
            .first()

        // then
            sut.packagee?.name shouldBeEqualTo "com.lemonappdev.sample"
    }

    @Test
    fun `interface child packagee`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/AppClass.kt".toOsSeparator())
            .interfaces()
            .withName("ParentSuperInterface")
            .children()
            .first()

        // then
            sut.packagee?.name shouldBeEqualTo "com.lemonappdev.sample"
    }
}
