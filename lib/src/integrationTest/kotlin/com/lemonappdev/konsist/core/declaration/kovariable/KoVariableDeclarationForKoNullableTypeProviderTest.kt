package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.enumConstants
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

class KoVariableDeclarationForKoNullableTypeProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesWithoutType")
    fun `variable-without-type`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            type shouldBeEqualTo null
            hasType() shouldBeEqualTo false
            hasType { it.name == "String" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo false
            hasType("String") shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValuesWithType")
    fun `variable-with-type`(declarations: List<KoVariableProvider>) {
        // given
        val sut = declarations
            .variables
            .first()

        // then
        assertSoftly(sut) {
            type?.name shouldBeEqualTo "String"
            hasType() shouldBeEqualTo true
            hasType { it.name == "String" } shouldBeEqualTo true
            hasType { it.name == "Int" } shouldBeEqualTo false
            hasTypeOf(String::class) shouldBeEqualTo true
            hasTypeOf(Int::class) shouldBeEqualTo false
            hasType("String") shouldBeEqualTo true
            hasType("Int") shouldBeEqualTo false
        }
    }

    companion object {
        private fun getSnippetFile(fileName: String) =
            TestSnippetProvider.getSnippetKoScope("core/declaration/kovariable/snippet/forkonullabletypeprovider/", fileName)

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithoutType() = listOf(
            arguments(getSnippetFile("variable-in-function-without-type").functions()),
            arguments(getSnippetFile("variable-in-init-block-without-type").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-without-type").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-without-type").properties().getters),
            arguments(getSnippetFile("variable-in-setter-without-type").properties().setters),
        )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesWithType() = listOf(
            arguments(getSnippetFile("variable-in-function-with-type").functions()),
            arguments(getSnippetFile("variable-in-init-block-with-type").classes().initBlocks),
            arguments(getSnippetFile("variable-in-enum-constant-with-type").classes().enumConstants),
            arguments(getSnippetFile("variable-in-getter-with-type").properties().getters),
            arguments(getSnippetFile("variable-in-setter-with-type").properties().setters),
        )
    }
}
