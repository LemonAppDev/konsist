package com.lemonappdev.konsist.declaration.type.kotype

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
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
import org.amshove.kluent.shouldNotBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeForKoTypeDeclarationProviderTest {
    @Test
    fun `parameter in class constructor has class type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .classes()
                .withName("ClassWithClassTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoClassDeclaration::class
            declaration shouldNotBeInstanceOf KoInterfaceDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.ClassType")
        }
    }

    @Test
    fun `parameter in function constructor has class type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .functions()
                .withName("functionWithClassTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoClassDeclaration::class
            declaration shouldNotBeInstanceOf KoInterfaceDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.ClassType")
        }
    }

    @Test
    fun `parameter in class constructor has interface type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .classes()
                .withName("ClassWithInterfaceTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.InterfaceType")
        }
    }

    @Test
    fun `parameter in function constructor has interface type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .functions()
                .withName("functionWithInterfaceTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoInterfaceDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.InterfaceType")
        }
    }

    @Test
    fun `parameter in class constructor has object type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .classes()
                .withName("ClassWithObjectTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoObjectDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.ObjectType")
        }
    }

    @Test
    fun `parameter in function constructor has object type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .functions()
                .withName("functionWithObjectTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoObjectDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.ObjectType")
        }
    }

    @Test
    fun `parameter in class constructor has typealias type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .classes()
                .withName("ClassWithTypeAliasTypeParameter")
                .primaryConstructors
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.TypeAliasType")
        }
    }

    @Test
    fun `parameter in function constructor has typealias type declaration which resides in the same package as declaration`() {
        // given
        val sut =
            Konsist
                .scopeFromFile("$appMainSourceSetProjectDirectory/sample/fortypetest/DeclarationsWithParameter.kt".toOsSeparator())
                .functions()
                .withName("functionWithTypeAliasTypeParameter")
                .parameters
                .first()
                .type

        // then
        assertSoftly(sut) {
            declaration shouldBeInstanceOf KoTypeAliasDeclaration::class
            declaration shouldNotBeInstanceOf KoClassDeclaration::class
            (declaration as? KoFullyQualifiedNameProvider)
                ?.fullyQualifiedName
                .shouldBeEqualTo("com.lemonappdev.sample.fortypetest.TypeAliasType")
        }
    }
}
