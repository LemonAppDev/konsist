package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.enumConstants
import com.lemonappdev.konsist.api.ext.list.getters
import com.lemonappdev.konsist.api.ext.list.initBlocks
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.api.ext.list.setters
import com.lemonappdev.konsist.api.ext.list.variables
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import io.kotest.mpp.file
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoParentDeclarationForKoInterfaceDelegateProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForDelegate")
    fun `class-with-parent-has-delegate`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .parents()
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo "sampleProperty"
            hasDelegate() shouldBeEqualTo true
            hasDelegate("sampleProperty") shouldBeEqualTo true
            hasDelegate("otherProperty") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesForNoDelegate")
    fun `class-with-parent-has-no-delegate`(fileName: String) {
        // given
        val sut = getSnippetFile("class-with-parent-class-from-file")
            .classes()
            .parents()
            .first()

        // then
        assertSoftly(sut) {
            delegateName shouldBeEqualTo null
            hasDelegate() shouldBeEqualTo false
            hasDelegate("sampleProperty") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkointerfacedelegateprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForDelegate() =
            listOf(
                arguments("class-with-parent-by-delegation-from-file"),
                arguments("class-with-parent-by-delegation-from-import"),
                arguments("class-with-external-parent-by-delegation"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForNoDelegate() =
            listOf(
                arguments("class-with-parent-class-from-file"),
                arguments("class-with-generic-parent-class-from-file"),
                arguments("class-with-parametrized-parent-class-from-file"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file"),
                arguments("class-with-parent-interface-from-file"),
                arguments("class-with-generic-parent-interface-from-file"),
                arguments("class-with-multiline-parent-from-file"),
                arguments("class-with-parent-class-from-import"),
                arguments("class-with-generic-parent-class-from-import"),
                arguments("class-with-parametrized-parent-class-from-import"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import"),
                arguments("class-with-parent-interface-from-import"),
                arguments("class-with-generic-parent-interface-from-import"),
                arguments("class-with-multiline-parent-from-import"),
                arguments("class-with-external-parent-class"),
                arguments("class-with-generic-external-parent-class"),
                arguments("class-with-parametrized-external-parent-class"),
                arguments("class-with-parametrized-and-generic-external-parent-class"),
                arguments("class-with-external-parent-interface"),
                arguments("class-with-generic-external-parent-interface"),
            )
    }
}
