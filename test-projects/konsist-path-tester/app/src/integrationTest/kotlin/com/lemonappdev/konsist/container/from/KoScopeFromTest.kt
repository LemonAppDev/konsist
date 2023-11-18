package com.lemonappdev.konsist.container.from

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.helper.ext.mapToFilePaths
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import com.lemonappdev.konsist.helper.util.PathProvider.appIntegrationTestSourceSetDirectory
import com.lemonappdev.konsist.helper.util.PathProvider.dataTestSourceSetDirectory
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class KoScopeFromTest {
    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromTest`() {
        // given
        val sut = Konsist
            .scopeFromTest()
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoFullyQualifiedName"+
"ProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoNameProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoResideInPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassDeclarationForKoHasTestClassProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromTest, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoFullyQualifiedName"+
"ProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoNameProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoResideInPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassDeclarationForKoHasTestClassProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromTest, app module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoFullyQualifiedName"+
"ProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoNameProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoResideInPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassDeclarationForKoHasTestClassProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, app module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "app", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Suppress("detekt.LongMethod")
    @Test
    fun `scopeFromTest, app module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$appIntegrationTestSourceSetDirectory/konsist/container/KoScopeTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromDirectoryTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFileTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromFilesTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromModuleTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromPackageTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProductionTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromProjectTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromSourceSetTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/container/from/KoScopeFromTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koannotation/KoAnnotationForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koargument/KoArgumentForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoFullyQualifiedName"+
"ProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoNameProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kochild/KoChildDeclarationForKoResideInPackageProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassDeclarationForKoHasTestClassProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koclass/KoClassForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koconstructor/KoConstructorForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koenumconstant/KoEnumConstantForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofile/KoFileForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kofunction/KoFunctionForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kogetter/KoGetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koimport/KoImportForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koinitblock/KoInitBlockForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoChildProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kointerface/KoInterfaceForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kokdoc/KoKDocForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koobject/KoObjectForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kopackage/KoPackageForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koparameter/KoParameterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/koproperty/KoPropertyForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kosetter/KoSetterForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotype/KoTypeForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kotypealias/KoTypeAliasForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoModuleProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/declaration/kovariable/KoVariableForKoSourceSetProviderTest.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/KoScopeExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/ext/PathExt.kt",
                "$appIntegrationTestSourceSetDirectory/konsist/helper/util/PathProvider.kt",
                "$appIntegrationTestSourceSetDirectory/sample/AppClassTest.kt",
                "$appIntegrationTestSourceSetDirectory/sample/data/AppDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, app module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "app", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromTest, data module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, data module, main source set`() {
        // given
        val func = { Konsist.scopeFromTest(moduleName = "data", sourceSetName = "main") }

        // then
        val message = "Source set 'main' is a production source set, but it should be test source set."
        func shouldThrow IllegalArgumentException::class withMessage message
    }

    @Test
    fun `scopeFromTest, data module, integrationTest source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data", sourceSetName = "integrationTest")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }

    @Test
    fun `scopeFromTest, data module, test source set`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "data", sourceSetName = "test")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(
            listOf(
                "$dataTestSourceSetDirectory/sample/LibClassSpec.kt",
                "$dataTestSourceSetDirectory/sample/LibClassTest.kt",
                "$dataTestSourceSetDirectory/sample/data/LibDataClassTest.kt",
            ).toOsSeparator(),
        )
    }

    @Test
    fun `scopeFromTest, root module`() {
        // given
        val sut = Konsist
            .scopeFromTest(moduleName = "root")
            .mapToFilePaths()

        // then
        sut.shouldBeEqualTo(emptyList())
    }
}
