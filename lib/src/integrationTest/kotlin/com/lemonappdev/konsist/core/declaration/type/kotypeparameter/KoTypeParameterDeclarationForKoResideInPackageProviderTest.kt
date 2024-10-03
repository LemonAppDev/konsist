package com.lemonappdev.konsist.core.declaration.type.kotypeparameter

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.provider.parameters
import com.lemonappdev.konsist.api.ext.list.provider.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.provider.properties
import com.lemonappdev.konsist.api.ext.list.provider.returnTypes
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterDeclarationForKoResideInPackageProviderTest {
    @Test
    fun `function-type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("function-type-parameter-reside-in-package")
                .functions()
                .returnTypes
                .firstOrNull()
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @Test
    fun `class-type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("class-type-parameter-reside-in-package")
                .classes()
                .primaryConstructors
                .parameters
                .first()
                .type
                .asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("interface-type-parameter-reside-in-package")
                .interfaces()
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @Test
    fun `property-type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("property-type-parameter-reside-in-package")
                .properties()
                .first()
                .type
                ?.asTypeParameterDeclaration()

        // then
        assertSoftly(sut) {
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-type-parameter-reside-in-package`() {
        // given
        val sut =
            getSnippetFile("typealias-type-parameter-reside-in-package")
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
            it?.resideInPackage("com..") shouldBeEqualTo true
            it?.resideInPackage("com") shouldBeEqualTo false
            it?.resideOutsidePackage("com..") shouldBeEqualTo false
            it?.resideOutsidePackage("com") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/type/kotypeparameter/snippet/forkoresideinpackageprovider/", fileName)
}
