package com.lemonappdev.konsist.declaration.type.kotype

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportAliasDeclaration
import com.lemonappdev.konsist.api.declaration.KoInterfaceDeclaration
import com.lemonappdev.konsist.api.declaration.KoObjectDeclaration
import com.lemonappdev.konsist.api.declaration.KoTypeAliasDeclaration
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.primaryConstructors
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appMainSourceSetProjectDirectory
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeForKoTypeDeclarationProviderTest {
    @Test
    fun `parameter in class constructor has class type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .classes()
                .withName("ClassWithClassTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.ClassType")
        }
    }

    @Test
    fun `parameter in function constructor has class type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .functions()
                .withName("functionWithClassTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.ClassType")
        }
    }

    @Test
    fun `parameter in class constructor has interface type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .classes()
                .withName("ClassWithInterfaceTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.InterfaceType")
        }
    }

    @Test
    fun `parameter in function constructor has interface type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .functions()
                .withName("functionWithInterfaceTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.InterfaceType")
        }
    }

    @Test
    fun `parameter in class constructor has object type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .classes()
                .withName("ClassWithObjectTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.ObjectType")
        }
    }

    @Test
    fun `parameter in function constructor has object type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .functions()
                .withName("functionWithObjectTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.ObjectType")
        }
    }

    @Test
    fun `parameter in class constructor has typealias type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .classes()
                .withName("ClassWithTypeAliasTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.TypeAliasType")
        }
    }

    @Test
    fun `parameter in function constructor has typealias type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/packagecase/DeclarationsWithParameter.kt".toOsSeparator()
                )
                .functions()
                .withName("functionWithTypeAliasTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.packagecase.TypeAliasType")
        }
    }

    @Test
    fun `parameter in class constructor has class type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithClassTypeWithImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType")
        }
    }

    @Test
    fun `parameter in function constructor has class type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithClassTypeWithImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType")
        }
    }

    @Test
    fun `parameter in class constructor has interface type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithInterfaceTypeWithImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType")
        }
    }

    @Test
    fun `parameter in function constructor has interface type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithInterfaceTypeWithImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType")
        }
    }

    @Test
    fun `parameter in class constructor has object type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithObjectTypeWithImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType")
        }
    }

    @Test
    fun `parameter in function constructor has object type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithObjectTypeWithImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType")
        }
    }

    @Test
    fun `parameter in class constructor has typealias type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithTypeAliasTypeWithImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType")
        }
    }

    @Test
    fun `parameter in function constructor has typealias type declaration which was imported using import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithTypeAliasTypeWithImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoImportAliasDeclaration::class
            asImportAliasDeclaration()?.importDirective
                ?.name
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType")
        }
    }

    @Test
    fun `parameter in class constructor has class type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithClassTypeWithoutImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType")
        }
    }

    @Test
    fun `parameter in function constructor has class type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithClassTypeWithoutImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoClassDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ClassType")
        }
    }

    @Test
    fun `parameter in class constructor has interface type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithInterfaceTypeWithoutImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType")
        }
    }

    @Test
    fun `parameter in function constructor has interface type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithInterfaceTypeWithoutImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoInterfaceDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.InterfaceType")
        }
    }

    @Test
    fun `parameter in class constructor has object type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithObjectTypeWithoutImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType")
        }
    }

    @Test
    fun `parameter in function constructor has object type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithObjectTypeWithoutImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoObjectDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.ObjectType")
        }
    }

    @Test
    fun `parameter in class constructor has typealias type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .classes()
                .withName("ClassContainingParameterWithTypeAliasTypeWithoutImportAlias")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType")
        }
    }

    @Test
    fun `parameter in function constructor has typealias type declaration which was imported without import alias`() {
        // given
        val sut =
            Konsist
                .scopeFromFile(
                    "$appMainSourceSetProjectDirectory/sample/fortypetest/importaliascase/DeclarationsWithoutImportAliasType.kt"
                        .toOsSeparator()
                )
                .functions()
                .withName("functionContainingParameterWithTypeAliasTypeWithoutImportAlias")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            sourceDeclaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            (sourceDeclaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.importaliascase.declarations.TypeAliasType")
        }
    }
}
