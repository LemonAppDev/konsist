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
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForContainingSourceSetName {
    private val main = "main"
    private val test = "test"
    private val integrationTest = "integrationTest"
    @Test
    fun `containing source set name is 'main' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetDirectory/sample/AppClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            containingSourceSetName shouldBeEqualTo main
            resideInSourceSet(main) shouldBeEqualTo true
            resideInSourceSet(test) shouldBeEqualTo false
        }
    }

    @Test
    fun `containing source set name is 'integrationTest' in app module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt", absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            containingSourceSetName shouldBeEqualTo integrationTest
            resideInSourceSet(integrationTest) shouldBeEqualTo true
            resideInSourceSet(test) shouldBeEqualTo false
        }
    }

    @Test
    fun `containing source set name is 'main' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataMainSourceSetDirectory/sample/LibClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            containingSourceSetName shouldBeEqualTo main
            resideInSourceSet(main) shouldBeEqualTo true
            resideInSourceSet(test) shouldBeEqualTo false
        }
    }

    @Test
    fun `containing source set name is 'test' in data module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$dataTestSourceSetDirectory/sample/LibClassTest.kt", absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            containingSourceSetName shouldBeEqualTo test
            resideInSourceSet(test) shouldBeEqualTo true
            resideInSourceSet(integrationTest) shouldBeEqualTo false
        }
    }

    @Test
    fun `containing source set name is 'main' in root module`() {
        // given
        val sut = Konsist
            .scopeFromFile("$rootMainSourceSetDirectory/sample/RootClass.kt", absolutePath = true)
            .files()
            .first()

        // then
        assertSoftly(sut) {
            containingSourceSetName shouldBeEqualTo main
            resideInSourceSet(main) shouldBeEqualTo true
            resideInSourceSet(test) shouldBeEqualTo false
        }
    }
}
