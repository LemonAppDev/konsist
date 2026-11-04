package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoVariableDeclarationForKoResideInPackageProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesWithoutPackage")
    fun `variable-not-reside-in-file-package`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            residesInPackage("com") shouldBeEqualTo false
            residesOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithPackage")
    fun `variable-reside-in-file-package`(declarations: List<KoVariableProvider>) {
        // given
        val sut =
            declarations
                .variables
                .first()

        // then
        assertSoftly(sut) {
            residesInPackage("com..") shouldBeEqualTo true
            residesInPackage("com") shouldBeEqualTo false
            residesOutsidePackage("com..") shouldBeEqualTo false
            residesOutsidePackage("com") shouldBeEqualTo true
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            getSnippetKoScope("core/declaration/kovariable/snippet/forkoresideinpackageprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithoutPackage() =
            listOf(
                arguments(getSnippetFile("variable-in-function-not-reside-in-file-package").functions()),
                arguments(getSnippetFile("variable-in-init-block-not-reside-in-file-package").classes().initBlocks),
                arguments(getSnippetFile("variable-in-getter-not-reside-in-file-package").properties().getters),
                arguments(getSnippetFile("variable-in-setter-not-reside-in-file-package").properties().setters),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithPackage() =
            listOf(
                arguments(getSnippetFile("variable-in-function-reside-in-file-package").functions()),
                arguments(getSnippetFile("variable-in-init-block-reside-in-file-package").classes().initBlocks),
                arguments(getSnippetFile("variable-in-getter-reside-in-file-package").properties().getters),
                arguments(getSnippetFile("variable-in-setter-reside-in-file-package").properties().setters),
            )
    }
}
