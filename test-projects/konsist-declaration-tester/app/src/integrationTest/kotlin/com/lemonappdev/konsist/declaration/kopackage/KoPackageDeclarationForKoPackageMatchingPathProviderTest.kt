package com.lemonappdev.konsist.declaration.kopackage

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoPackageMatchingPathProviderTest {
    @Test
    fun `package-with-matching-file-path`() {
        // given
        val sut = Konsist
            .scopeFromFile("${PathProvider.appMainSourceSetProjectDirectory}/sample/AppClass.kt".toOsSeparator())
            .packages
            .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo true
    }

    @Test
    fun `package-without-matching-file-path`() {
        // given
        val sut = Konsist
            .scopeFromFile(
                "${PathProvider.appMainSourceSetProjectDirectory}/sample/AppClassWithPackageNotMatchingToPath.kt".toOsSeparator()
            )
            .packages
            .first()

        // then
        sut.hasMatchingPath shouldBeEqualTo false
    }
}
