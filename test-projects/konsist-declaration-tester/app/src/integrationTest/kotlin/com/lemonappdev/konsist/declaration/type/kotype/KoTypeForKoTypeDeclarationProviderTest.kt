package com.lemonappdev.konsist.declaration.type.kotype

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import com.lemonappdev.sample.ClassType.NestedClassType
import com.lemonappdev.sample.InterfaceType.NestedInterfaceType
import com.lemonappdev.sample.ObjectType.NestedObjectType
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeForKoTypeDeclarationProviderTest {
    @Test
    fun `class-type`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/FileWithTypeParameters.kt".toOsSeparator())
            .classes()
            .first { it.name == "FirstClass" }
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.hasDeclaration { declaration -> declaration.name == "NestedClassType" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(NestedClassType::class) shouldBeEqualTo true
            it?.hasDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeInstanceOf KoClassDeclaration::class
            it?.asClassDeclaration()?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.sample.ClassType.NestedClassType"
            it?.hasClassDeclaration() shouldBeEqualTo true
            it
                ?.hasClassDeclaration {
                    declaration -> declaration.fullyQualifiedName == "com.lemonappdev.sample.ClassType.NestedClassType"
                }
                .shouldBeEqualTo(true)
            it
                ?.hasClassDeclaration { declaration -> declaration.fullyQualifiedName == "com.lemonappdev.sample.ClassType.OtherName" }
                .shouldBeEqualTo(false)
            it?.hasClassDeclarationOf(NestedClassType::class) shouldBeEqualTo true
            it?.hasClassDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.isClass shouldBeEqualTo true
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isFunctionType shouldBeEqualTo false
            it?.isExternalType shouldBeEqualTo false
        }
    }

    @Test
    fun `object-type`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/FileWithTypeParameters.kt".toOsSeparator())
            .classes()
            .first { it.name == "SecondClass" }
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.hasDeclaration { declaration -> declaration.name == "NestedObjectType" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(NestedObjectType::class) shouldBeEqualTo true
            it?.hasDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeInstanceOf KoObjectDeclaration::class
            it?.asObjectDeclaration()?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.sample.ObjectType.NestedObjectType"
            it?.hasObjectDeclaration() shouldBeEqualTo true
            it
                ?.hasObjectDeclaration { declaration ->
                    declaration.fullyQualifiedName == "com.lemonappdev.sample.ObjectType.NestedObjectType"
                }
                .shouldBeEqualTo.(true)
            it
                ?.hasObjectDeclaration { declaration ->
                    declaration.fullyQualifiedName == "com.lemonappdev.sample.ObjectType.OtherName"
                }
                .shouldBeEqualTo.(false)
            it?.hasObjectDeclarationOf(NestedObjectType::class) shouldBeEqualTo true
            it?.hasObjectDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(NestedObjectType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeEqualTo null
            it?.hasInterfaceDeclaration() shouldBeEqualTo false
            it?.hasInterfaceDeclarationOf(NestedObjectType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(NestedObjectType::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo true
            it?.isInterface shouldBeEqualTo false
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isFunctionType shouldBeEqualTo false
            it?.isExternalType shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-type`() {
        // given
        val sut = Konsist
            .scopeFromFile("$appMainSourceSetProjectDirectory/sample/FileWithTypeParameters.kt".toOsSeparator())
            .classes()
            .first { it.name == "ThirdClass" }
            .primaryConstructor
            ?.parameters
            ?.first()
            ?.type

        // then
        assertSoftly(sut) {
            it?.hasDeclaration { declaration -> declaration.name == "NestedInterfaceType" } shouldBeEqualTo true
            it?.hasDeclaration { declaration -> declaration.name == "OtherName" } shouldBeEqualTo false
            it?.hasDeclarationOf(NestedInterfaceType::class) shouldBeEqualTo true
            it?.hasDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asInterfaceDeclaration() shouldBeInstanceOf KoInterfaceDeclaration::class
            it?.asInterfaceDeclaration()?.fullyQualifiedName shouldBeEqualTo "com.lemonappdev.sample.InterfaceType.NestedInterfaceType"
            it?.hasInterfaceDeclaration() shouldBeEqualTo true
            it
                ?.hasInterfaceDeclaration { declaration ->
                    declaration.fullyQualifiedName == "com.lemonappdev.sample.InterfaceType.NestedInterfaceType"
                }
                .shouldBeEqualTo(true)
            it
                ?.hasInterfaceDeclaration { declaration ->
                    declaration.fullyQualifiedName == "com.lemonappdev.sample.InterfaceType.OtherName"
                }
                .shouldBeEqualTo(false)
            it?.hasInterfaceDeclarationOf(NestedInterfaceType::class) shouldBeEqualTo true
            it?.hasInterfaceDeclarationOf(NestedClassType::class) shouldBeEqualTo false
            it?.asClassDeclaration() shouldBeEqualTo null
            it?.hasClassDeclaration() shouldBeEqualTo false
            it?.hasClassDeclarationOf(NestedInterfaceType::class) shouldBeEqualTo false
            it?.asObjectDeclaration() shouldBeEqualTo null
            it?.hasObjectDeclaration() shouldBeEqualTo false
            it?.hasObjectDeclarationOf(NestedInterfaceType::class) shouldBeEqualTo false
            it?.asTypeAliasDeclaration() shouldBeEqualTo null
            it?.hasTypeAliasDeclaration() shouldBeEqualTo false
            it?.asImportAliasDeclaration() shouldBeEqualTo null
            it?.hasImportAliasDeclaration() shouldBeEqualTo false
            it?.asKotlinTypeDeclaration() shouldBeEqualTo null
            it?.hasKotlinTypeDeclaration() shouldBeEqualTo false
            it?.hasKotlinTypeDeclarationOf(NestedInterfaceType::class) shouldBeEqualTo false
            it?.asFunctionTypeDeclaration() shouldBeEqualTo null
            it?.hasFunctionTypeDeclaration() shouldBeEqualTo false
            it?.asExternalTypeDeclaration() shouldBeEqualTo null
            it?.hasExternalTypeDeclaration() shouldBeEqualTo false
            it?.isClass shouldBeEqualTo false
            it?.isObject shouldBeEqualTo false
            it?.isInterface shouldBeEqualTo true
            it?.isTypeAlias shouldBeEqualTo false
            it?.isImportAlias shouldBeEqualTo false
            it?.isKotlinType shouldBeEqualTo false
            it?.isFunctionType shouldBeEqualTo false
            it?.isExternalType shouldBeEqualTo false
        }
    }
}
