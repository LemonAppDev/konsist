package com.lemonappdev.konsist.kofile

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.rootMainSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.projectRootDirectory
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.util.PathProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForContainingSourceSetName {
    @Test
    fun `containing source set name is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "main"
    }

    @Test
    fun `containing source set name is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt", absolutePath = true)
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "integrationTest"
    }

    @Test
    fun `containing source set name is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetDirectory/sample/LibClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "main"
    }

    @Test
    fun `containing source set name is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetDirectory/sample/LibClassTest.kt", absolutePath = true)
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "test"
    }

    @Test
    fun `containing source set name is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetDirectory/sample/RootClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        sut.containingSourceSetName shouldBeEqualTo "main"
    }
}
