package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeDeclarationForKoPackageProviderTest {
    @ParameterizedTest
    @MethodSource("provideValuesForTypeOutsidePackage")
    fun `type-is-not-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        sut?.packagee shouldBeEqualTo null
    }

    @ParameterizedTest
    @MethodSource("provideValuesForTypeInsidePackage")
    fun `type-is-in-package`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.packagee shouldNotBeEqualTo null
            it?.packagee?.fullyQualifiedName shouldBeEqualTo "com.samplepackage"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/type/kotype/snippet/forkopackageprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTypeInsidePackage() =
            listOf(
                arguments("nullable-kotlin-basic-type-is-in-package"),
                arguments("not-nullable-kotlin-basic-type-is-in-package"),
                arguments("nullable-kotlin-collection-type-is-in-package"),
                arguments("not-nullable-kotlin-collection-type-is-in-package"),
                arguments("nullable-class-type-is-in-package"),
                arguments("not-nullable-class-type-is-in-package"),
                arguments("nullable-interface-type-is-in-package"),
                arguments("not-nullable-interface-type-is-in-package"),
                arguments("nullable-object-type-is-in-package"),
                arguments("not-nullable-object-type-is-in-package"),
                arguments("nullable-function-type-is-in-package"),
                arguments("not-nullable-function-type-is-in-package"),
                arguments("nullable-import-alias-type-is-in-package"),
                arguments("not-nullable-import-alias-type-is-in-package"),
                arguments("nullable-typealias-type-is-in-package"),
                arguments("not-nullable-typealias-type-is-in-package"),
                arguments("nullable-external-type-is-in-package"),
                arguments("not-nullable-external-type-is-in-package"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideValuesForTypeOutsidePackage() =
            listOf(
                arguments("nullable-kotlin-basic-type-is-not-in-package"),
                arguments("not-nullable-kotlin-basic-type-is-not-in-package"),
                arguments("nullable-kotlin-collection-type-is-not-in-package"),
                arguments("not-nullable-kotlin-collection-type-is-not-in-package"),
                arguments("nullable-class-type-is-not-in-package"),
                arguments("not-nullable-class-type-is-not-in-package"),
                arguments("nullable-interface-type-is-not-in-package"),
                arguments("not-nullable-interface-type-is-not-in-package"),
                arguments("nullable-object-type-is-not-in-package"),
                arguments("not-nullable-object-type-is-not-in-package"),
                arguments("nullable-function-type-is-not-in-package"),
                arguments("not-nullable-function-type-is-not-in-package"),
                arguments("nullable-import-alias-type-is-not-in-package"),
                arguments("not-nullable-import-alias-type-is-not-in-package"),
                arguments("nullable-typealias-type-is-not-in-package"),
                arguments("not-nullable-typealias-type-is-not-in-package"),
                arguments("nullable-external-type-is-not-in-package"),
                arguments("not-nullable-external-type-is-not-in-package"),
            )
    }
}
