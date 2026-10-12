package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.cache.KoPropertyCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoClassProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoOrdinalProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoPropertyProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoVariableProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import com.lemonappdev.konsist.core.provider.util.KoLocalDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtEnumEntry
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

@Suppress("detekt.TooManyFunctions")
internal class KoEnumConstantDeclarationCore private constructor(
    override val ktEnumEntry: KtEnumEntry,
    override val containingDeclaration: KoBaseDeclaration,
) : KoEnumConstantDeclaration,
    KoBaseProviderCore,
    KoAnnotationProviderCore,
    KoArgumentProviderCore,
    KoContainingFileProviderCore,
    KoEnumNameProviderCore,
    KoKDocProviderCore,
    @RemoveInVersion("0.20.0")
    KoLocalClassProviderCore,
    @RemoveInVersion("0.20.0")
    KoLocalDeclarationProviderCore,
    @RemoveInVersion("0.20.0")
    KoLocalFunctionProviderCore,
    @RemoveInVersion("0.20.0")
    KoVariableProviderCore,
    KoClassProviderCore,
    KoDeclarationProviderCore,
    KoFunctionProviderCore,
    KoPropertyProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoOrdinalProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner
        get() = ktEnumEntry

    override val ktAnnotationEntries: List<KtAnnotationEntry>?
        get() = ktEnumEntry.annotationEntries

    override val psiElement: PsiElement
        get() = ktEnumEntry

    override val ktElement: KtElement
        get() = ktEnumEntry

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(ktEnumEntry, includeNested, includeLocal, this)

    @Volatile
    private var cachedArguments: Any? = KoPropertyCache.NOT_COMPUTED

    override val arguments: List<KoArgumentDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedArguments, { cachedArguments = it }) {
                ktEnumEntry
                    .initializerList
                    ?.initializers
                    ?.firstOrNull()
                    ?.children
                    ?.filterIsInstance<KtValueArgumentList>()
                    ?.firstOrNull()
                    ?.children
                    ?.filterIsInstance<KtValueArgument>()
                    ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
                    .orEmpty()
            }

    override fun toString(): String = name

    @Volatile
    private var cachedVariables: Any? = KoPropertyCache.NOT_COMPUTED

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("properties()"))
    override val variables: List<KoVariableDeclaration>
        get() = KoPropertyCache.getOrCompute(cachedVariables, { cachedVariables = it }) { super<KoVariableProviderCore>.variables }

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("numProperties()"))
    override val numVariables: Int
        get() = super<KoVariableProviderCore>.numVariables

    @Volatile
    private var cachedLocalDeclarations: Any? = KoPropertyCache.NOT_COMPUTED

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("declarations()"))
    override val localDeclarations: List<KoBaseDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedLocalDeclarations, { cachedLocalDeclarations = it }) {
                val psiElements =
                    ktEnumEntry
                        .body
                        ?.children

                KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
            }

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("numDeclarations()"))
    override val numLocalDeclarations: Int
        get() = super<KoLocalDeclarationProviderCore>.numLocalDeclarations

    @Volatile
    private var cachedLocalClasses: Any? = KoPropertyCache.NOT_COMPUTED

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("classes()"))
    override val localClasses: List<KoClassDeclaration>
        get() =
            KoPropertyCache.getOrCompute(
                cachedLocalClasses,
                { cachedLocalClasses = it },
            ) { super<KoLocalClassProviderCore>.localClasses }

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("numClasses()"))
    override val numLocalClasses: Int
        get() = super<KoLocalClassProviderCore>.numLocalClasses

    @Volatile
    private var cachedLocalFunctions: Any? = KoPropertyCache.NOT_COMPUTED

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("functions()"))
    override val localFunctions: List<KoFunctionDeclaration>
        get() =
            KoPropertyCache.getOrCompute(cachedLocalFunctions, {
                cachedLocalFunctions = it
            }) { super<KoLocalFunctionProviderCore>.localFunctions }

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("numFunctions()"))
    override val numLocalFunctions: Int
        get() = super<KoLocalFunctionProviderCore>.numLocalFunctions

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("countProperties()"))
    override fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int =
        super<KoVariableProviderCore>.countVariables(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasProperties()"))
    override fun hasVariables(): Boolean = super<KoVariableProviderCore>.hasVariables()

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoVariableProviderCore>.hasVariableWithName(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoVariableProviderCore>.hasVariableWithName(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoVariableProviderCore>.hasVariablesWithAllNames(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoVariableProviderCore>.hasVariablesWithAllNames(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasProperty()"))
    override fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean = super<KoVariableProviderCore>.hasVariable(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasAllProperties()"))
    override fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean =
        super<KoVariableProviderCore>.hasAllVariables(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("countClasses()"))
    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int =
        super<KoLocalClassProviderCore>.countLocalClasses(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClasses()"))
    override fun hasLocalClasses(): Boolean = super<KoLocalClassProviderCore>.hasLocalClasses()

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalClassProviderCore>.hasLocalClassWithName(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalClassProviderCore>.hasLocalClassWithName(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalClassProviderCore>.hasLocalClassesWithAllNames(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalClassProviderCore>.hasLocalClassesWithAllNames(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasClass()"))
    override fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClass(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasAllClasses()"))
    override fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean =
        super<KoLocalClassProviderCore>.hasAllLocalClasses(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("countDeclarations()"))
    override fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int =
        super<KoLocalDeclarationProviderCore>.countLocalDeclarations(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasDeclarations()"))
    override fun hasLocalDeclarations(): Boolean = super<KoLocalDeclarationProviderCore>.hasLocalDeclarations()

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasDeclaration()"))
    override fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean =
        super<KoLocalDeclarationProviderCore>.hasLocalDeclaration(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasAllDeclarations()"))
    override fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean =
        super<KoLocalDeclarationProviderCore>.hasAllLocalDeclarations(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("countFunctions()"))
    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int =
        super<KoLocalFunctionProviderCore>.countLocalFunctions(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunctions()"))
    override fun hasLocalFunctions(): Boolean = super<KoLocalFunctionProviderCore>.hasLocalFunctions()

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalFunctionProviderCore>.hasLocalFunctionWithName(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalFunctionProviderCore>.hasLocalFunctionWithName(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(
        name: String,
        vararg names: String,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalFunctionProviderCore>.hasLocalFunctionsWithAllNames(name, *names, ignoreCase = ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(
        names: Collection<String>,
        ignoreCase: Boolean,
    ): Boolean = super<KoLocalFunctionProviderCore>.hasLocalFunctionsWithAllNames(names, ignoreCase)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasFunction()"))
    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunction(predicate)

    @Deprecated("Will be removed in version 1.0.0-beta.1", replaceWith = ReplaceWith("hasAllFunctions()"))
    override fun hasAllLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Boolean =
        super<KoLocalFunctionProviderCore>.hasAllLocalFunctions(predicate)

    internal companion object {
        private val cache: KoDeclarationCache<KoEnumConstantDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktEnumEntry: KtEnumEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoEnumConstantDeclaration =
            cache.getOrCreateInstance(ktEnumEntry, containingDeclaration) {
                KoEnumConstantDeclarationCore(ktEnumEntry, containingDeclaration)
            }
    }
}
