package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoClassProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoEnumNameProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
import com.lemonappdev.konsist.api.provider.KoPropertyProvider
import com.lemonappdev.konsist.api.provider.KoResideInPackageProvider
import com.lemonappdev.konsist.api.provider.KoSourceSetProvider
import com.lemonappdev.konsist.api.provider.KoTextProvider
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import com.lemonappdev.konsist.core.annotation.RemoveInVersion

/**
 * Represents a Kotlin enum constant declaration.
 */
interface KoEnumConstantDeclaration :
    KoBaseDeclaration,
    KoBaseProvider,
    KoAnnotationProvider,
    KoArgumentProvider,
    KoContainingFileProvider,
    KoEnumNameProvider,
    KoFullyQualifiedNameProvider,
    KoKDocProvider,
    @RemoveInVersion("0.20.0")
    KoLocalClassProvider,
    @RemoveInVersion("0.20.0")
    KoLocalDeclarationProvider,
    @RemoveInVersion("0.20.0")
    KoLocalFunctionProvider,
    @RemoveInVersion("0.20.0")
    KoVariableProvider,
    KoClassProvider,
    KoDeclarationProvider,
    KoFunctionProvider,
    KoPropertyProvider,
    KoLocationProvider,
    KoNameProvider,
    KoPackageProvider,
    KoContainingDeclarationProvider,
    KoPathProvider,
    KoModuleProvider,
    KoSourceSetProvider,
    KoResideInPackageProvider,
    KoTextProvider {
    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("properties()"))
    override val variables: List<KoVariableDeclaration>

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("numProperties()"))
    override val numVariables: Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("classes()"))
    override val localClasses: List<KoClassDeclaration>

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("numClasses()"))
    override val numLocalClasses: Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("declarations()"))
    override val localDeclarations: List<KoBaseDeclaration>

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("numDeclarations()"))
    override val numLocalDeclarations: Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("countProperties()"))
    override fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasProperties()"))
    override fun hasVariables(): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasProperty()"))
    override fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasAllProperties()"))
    override fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("countClasses()"))
    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClasses()"))
    override fun hasLocalClasses(): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasClass()"))
    override fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasAllClasses()"))
    override fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("countDeclarations()"))
    override fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasDeclarations()"))
    override fun hasLocalDeclarations(): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasDeclaration()"))
    override fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasAllDeclarations()"))
    override fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("functions()"))
    override val localFunctions: List<KoFunctionDeclaration>

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("numFunctions()"))
    override val numLocalFunctions: Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("countFunctions()"))
    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunctions()"))
    override fun hasLocalFunctions(): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(names: Collection<String>): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasFunction()"))
    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean

    @Deprecated("Will be removed in version 0.20.0", ReplaceWith("hasAllFunctions()"))
    override fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean
}
