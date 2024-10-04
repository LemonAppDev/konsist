package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoTextProviderTest {
    @Test
    fun `function-type-parameter-text`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-text")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-type-parameter-text`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-text")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type-parameter-text`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-text")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-type-parameter-text`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-text")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-type-parameter-text`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-text")
                .typeAliases
                .first()
                .type
                .asGenericTypeDeclaration()
                ?.typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.text shouldBeEqualTo "TestType"
            it?.hasTextStartingWith("Test") shouldBeEqualTo true
            it?.hasTextStartingWith("Other") shouldBeEqualTo false
            it?.hasTextEndingWith("Type") shouldBeEqualTo true
            it?.hasTextEndingWith("other") shouldBeEqualTo false
            it?.hasTextContaining("stTy") shouldBeEqualTo true
            it?.hasTextContaining("anno") shouldBeEqualTo false
            it?.hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            it?.hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotypeparameter/snippet/forkotextprovider/", fileName)
}
