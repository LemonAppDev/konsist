package com.lemonappdev.konsist.core.declaration.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.returnTypes
import com.lemonappdev.konsist.core.declaration.type.KoStarProjectionDeclarationCore.hasName
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoNameProviderTest {
    @Test
    fun `function-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-name")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "TestType"
            it?.hasName("TestType") shouldBeEqualTo true
            it?.hasName("OtherType") shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-name")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "TestType"
            it?.hasName("TestType") shouldBeEqualTo true
            it?.hasName("OtherType") shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-name")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "TestType"
            it?.hasName("TestType") shouldBeEqualTo true
            it?.hasName("OtherType") shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `property-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-name")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "TestType"
            it?.hasName("TestType") shouldBeEqualTo true
            it?.hasName("OtherType") shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-type-parameter-name`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-name")
                .typeAliases
                .first()
                .type
                .typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.name shouldBeEqualTo "TestType"
            it?.hasName("TestType") shouldBeEqualTo true
            it?.hasName("OtherType") shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = false) shouldBeEqualTo false
            it?.hasName("testtype", ignoreCase = true) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-with-modifier-and-upper-bound-name`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-with-modifier-and-upper-bound-name")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            it.typeParameters.first().name shouldBeEqualTo "TestType"
            it.typeParameters.first().text shouldBeEqualTo "out TestType : Number"
            it.hasTypeParameterWithName("TestType") shouldBeEqualTo true
            it.hasTypeParameterWithName("out TestType : Number") shouldBeEqualTo false
        }
    }

    @Test
    fun `function-type-parameter-with-modifier-and-upper-bound-name`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-with-modifier-and-upper-bound-name")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            it.typeParameters.first().name shouldBeEqualTo "TestType"
            it.hasTypeParameterWithName("TestType") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeparameter/snippet/forkonameprovider/",
            fileName,
        )
}
