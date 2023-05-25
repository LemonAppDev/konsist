package com.lemonappdev.konsist.kofile

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForContainingSourceSetName {
    @Test
    fun `containing source set name is 'main'`() {
        // given
        val sut = Konsist
            .scopeFromFile("/sample/AppClass.kt")
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "main"
    }

}