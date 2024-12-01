package com.lemonappdev.konsist.api.declaration

import com.lemonappdev.konsist.api.provider.KoAnnotationProvider
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoContainingFileProvider
import com.lemonappdev.konsist.api.provider.KoEnumNameProvider
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoKDocProvider
import com.lemonappdev.konsist.api.provider.KoLocalClassProvider
import com.lemonappdev.konsist.api.provider.KoLocalDeclarationProvider
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.api.provider.KoLocationProvider
import com.lemonappdev.konsist.api.provider.KoModuleProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import com.lemonappdev.konsist.api.provider.KoPackageProvider
import com.lemonappdev.konsist.api.provider.KoPathProvider
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
    KoLocalClassProvider,
    KoLocalDeclarationProvider,
    KoLocalFunctionProvider,
    @RemoveInVersion("0.20.0")
    KoVariableProvider,
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
}
