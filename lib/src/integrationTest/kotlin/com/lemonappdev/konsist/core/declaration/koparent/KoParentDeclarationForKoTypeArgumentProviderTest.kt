package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.parents
import com.lemonappdev.konsist.testdata.NonExistingAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation
import com.lemonappdev.konsist.testdata.SampleAnnotation1
import com.lemonappdev.konsist.testdata.SampleAnnotation2
import com.lemonappdev.konsist.testdata.SampleClass
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

@Suppress("detekt.LongMethod")
class KoParentDeclarationForKoTypeArgumentProviderTest {
    @ParameterizedTest
    @MethodSource("provideClassesForNoAnnotation")
    fun `class-parent-has-no-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            typeArguments shouldBeEqualTo null
            numTypeArguments shouldBeEqualTo 0
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideClassesForTypeArgument")
    fun `class-parent-has-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .classes()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            name
            typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            typeArguments?.firstOrNull()?.name shouldBeEqualTo "Int"
            numTypeArguments shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("Int", "String") shouldBeEqualTo true
            hasTypeArgumentWithName("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("Int", "String")) shouldBeEqualTo true
            hasTypeArgumentWithName(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("Int") shouldBeEqualTo true
            hasTypeArgumentsWithAllNames("Int", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("Int")) shouldBeEqualTo true
            hasTypeArgumentsWithAllNames(listOf("Int", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentOf(Int::class, String::class) shouldBeEqualTo true
            hasTypeArgumentOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(Int::class, String::class)) shouldBeEqualTo true
            hasTypeArgumentOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(Int::class) shouldBeEqualTo true
            hasAllTypeArgumentsOf(Int::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(Int::class)) shouldBeEqualTo true
            hasAllTypeArgumentsOf(listOf(Int::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForNoAnnotation")
    fun `interface-parent-has-no-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            typeArguments shouldBeEqualTo null
            numTypeArguments shouldBeEqualTo 0
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideInterfacesForTypeArgument")
    fun `interface-parent-has-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .interfaces()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class

            typeArguments?.firstOrNull()?.name shouldBeEqualTo "Int"
            numTypeArguments shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("Int", "String") shouldBeEqualTo true
            hasTypeArgumentWithName("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("Int", "String")) shouldBeEqualTo true
            hasTypeArgumentWithName(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("Int") shouldBeEqualTo true
            hasTypeArgumentsWithAllNames("Int", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("Int")) shouldBeEqualTo true
            hasTypeArgumentsWithAllNames(listOf("Int", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentOf(Int::class, String::class) shouldBeEqualTo true
            hasTypeArgumentOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(Int::class, String::class)) shouldBeEqualTo true
            hasTypeArgumentOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(Int::class) shouldBeEqualTo true
            hasAllTypeArgumentsOf(Int::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(Int::class)) shouldBeEqualTo true
            hasAllTypeArgumentsOf(listOf(Int::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForNoAnnotation")
    fun `object-parent-has-no-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            typeArguments shouldBeEqualTo null
            numTypeArguments shouldBeEqualTo 0
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("String", "Int") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("String", "Int") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("String", "Int")) shouldBeEqualTo false
            hasTypeArgumentOf(String::class, Int::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(String::class, Int::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(String::class, Int::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, Int::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideObjectsForTypeArgument")
    fun `object-parent-has-type-argument`(fileName: String) {
        // given
        val sut =
            getSnippetFile(fileName)
                .objects()
                .parents()
                .first()

        // then
        assertSoftly(sut) {
            typeArguments
                ?.firstOrNull()
                ?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class

            typeArguments?.firstOrNull()?.name shouldBeEqualTo "Int"
            numTypeArguments shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo 1
            countTypeArguments { type -> type.sourceDeclaration?.isClass == true } shouldBeEqualTo 0
            hasTypeArgumentWithName("Int", "String") shouldBeEqualTo true
            hasTypeArgumentWithName("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentWithName(listOf("Int", "String")) shouldBeEqualTo true
            hasTypeArgumentWithName(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("Int") shouldBeEqualTo true
            hasTypeArgumentsWithAllNames("Int", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames("OtherClass", "String") shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("Int")) shouldBeEqualTo true
            hasTypeArgumentsWithAllNames(listOf("Int", "String")) shouldBeEqualTo false
            hasTypeArgumentsWithAllNames(listOf("OtherClass", "String")) shouldBeEqualTo false
            hasTypeArgumentOf(Int::class, String::class) shouldBeEqualTo true
            hasTypeArgumentOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasTypeArgumentOf(listOf(Int::class, String::class)) shouldBeEqualTo true
            hasTypeArgumentOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(Int::class) shouldBeEqualTo true
            hasAllTypeArgumentsOf(Int::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(SampleClass::class, String::class) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(Int::class)) shouldBeEqualTo true
            hasAllTypeArgumentsOf(listOf(Int::class, String::class)) shouldBeEqualTo false
            hasAllTypeArgumentsOf(listOf(SampleClass::class, String::class)) shouldBeEqualTo false
            hasTypeArgument { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasTypeArgument { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
            hasAllTypeArguments { type -> type.sourceDeclaration?.isKotlinType == true } shouldBeEqualTo true
            hasAllTypeArguments { type -> type.sourceDeclaration?.isExternal == true } shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparent/snippet/forkotypeargumentprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideClassesForNoAnnotation() =
            listOf(
                arguments("class-with-parent-class-from-file"),
                arguments("class-with-parametrized-parent-class-from-file"),
                arguments("class-with-parent-interface-from-file"),
                arguments("class-with-parent-by-delegation-from-file"),
                arguments("class-with-multiline-parent-from-file"),
                arguments("class-with-parent-class-from-import"),
                arguments("class-with-parametrized-parent-class-from-import"),
                arguments("class-with-parent-interface-from-import"),
                arguments("class-with-parent-by-delegation-from-import"),
                arguments("class-with-multiline-parent-from-import"),
                arguments("class-with-external-parent-class"),
                arguments("class-with-parametrized-external-parent-class"),
                arguments("class-with-external-parent-interface"),
                arguments("class-with-external-parent-by-delegation"),

                )

        @Suppress("unused")
        @JvmStatic
        fun provideClassesForTypeArgument() =
            listOf(
                arguments("class-with-generic-parent-class-from-file"),
                arguments("class-with-parametrized-and-generic-parent-class-from-file"),
                arguments("class-with-generic-parent-interface-from-file"),
                arguments("class-with-generic-parent-class-from-import"),
                arguments("class-with-parametrized-and-generic-parent-class-from-import"),
                arguments("class-with-generic-parent-interface-from-import"),
                arguments("class-with-generic-external-parent-class"),
                arguments("class-with-parametrized-and-generic-external-parent-class"),
                arguments("class-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForNoAnnotation() =
            listOf(
                arguments("interface-with-parent-interface-from-file"),
                arguments("interface-with-parent-interface-from-import"),
                arguments("interface-with-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideInterfacesForTypeArgument() =
            listOf(
                arguments("interface-with-generic-parent-interface-from-file"),
                arguments("interface-with-generic-parent-interface-from-import"),
                arguments("interface-with-generic-external-parent-interface"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForNoAnnotation() =
            listOf(
                arguments("object-with-parent-class-from-file"),
                arguments("object-with-parametrized-parent-class-from-file"),
                arguments("object-with-parent-interface-from-file"),
                arguments("object-with-multiline-parent-from-file"),
                arguments("object-with-parent-class-from-import"),
                arguments("object-with-parametrized-parent-class-from-import"),
                arguments("object-with-parent-interface-from-import"),
                arguments("object-with-multiline-parent-from-import"),
                arguments("object-with-external-parent-class"),
                arguments("object-with-parametrized-external-parent-class"),
                arguments("object-with-external-parent-interface"),
                arguments("object-with-multiline-external-parent"),
            )

        @Suppress("unused")
        @JvmStatic
        fun provideObjectsForTypeArgument() =
            listOf(
//                arguments("object-with-generic-parent-class-from-file"),
//                arguments("object-with-parametrized-and-generic-parent-class-from-file"),
                arguments("object-with-generic-parent-interface-from-file"),
//                arguments("object-with-generic-parent-class-from-import"),
//                arguments("object-with-parametrized-and-generic-parent-class-from-import"),
//                arguments("object-with-generic-parent-interface-from-import"),
//                arguments("object-with-generic-external-parent-class"),
//                arguments("object-with-parametrized-and-generic-external-parent-class"),
//                arguments("object-with-generic-external-parent-interface"),
            )
    }
}
