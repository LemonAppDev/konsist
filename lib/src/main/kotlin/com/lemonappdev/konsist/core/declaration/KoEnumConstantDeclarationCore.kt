package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoEnumConstantDeclaration
import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.core.annotation.RemoveInVersion
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoDeclarationFullyQualifiedNameProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumNameProviderCore
import com.lemonappdev.konsist.core.provider.KoKDocProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalClassProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoLocalFunctionProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
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
    KoPropertyProviderCore,
    KoLocationProviderCore,
    KoNameProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoDeclarationFullyQualifiedNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoTextProviderCore {
    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktEnumEntry }

    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy { ktEnumEntry.annotationEntries }

    override val psiElement: PsiElement by lazy { ktEnumEntry }

    override val ktElement: KtElement by lazy { ktEnumEntry }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean
    ): List<KoBaseDeclaration> = KoDeclarationProviderCoreUtil.getKoDeclarations(ktEnumEntry, includeNested, includeLocal, this)

    override val arguments: List<KoArgumentDeclaration> by lazy {
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

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("properties()"))
    override val variables: List<KoVariableDeclaration> by lazy { super<KoVariableProviderCore>.variables }

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("numProperties()"))
    override val numVariables: Int by lazy { super<KoVariableProviderCore>.numVariables }

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("declarations()"))
    override val localDeclarations: List<KoBaseDeclaration> by lazy {
        val psiElements =
            ktEnumEntry
                .body
                ?.children

        KoLocalDeclarationProviderCoreUtil.getKoLocalDeclarations(psiElements, this)
    }
    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("numDeclarations()"))
    override val numLocalDeclarations: Int
        get() = super<KoLocalDeclarationProviderCore>.numLocalDeclarations

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("classes()"))
    override val localClasses: List<KoClassDeclaration>
        get() = super<KoLocalClassProviderCore>.localClasses

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("numClasses()"))
    override val numLocalClasses: Int
        get() = super<KoLocalClassProviderCore>.numLocalClasses

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("functions()"))
    override val localFunctions: List<KoFunctionDeclaration>
        get() = super<KoLocalFunctionProviderCore>.localFunctions

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("numFunctions()"))
    override val numLocalFunctions: Int
        get() = super<KoLocalFunctionProviderCore>.numLocalFunctions

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("countProperties()"))
    override fun countVariables(predicate: (KoVariableDeclaration) -> Boolean): Int =
        super<KoVariableProviderCore>.countVariables(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasProperties()"))
    override fun hasVariables(): Boolean = super<KoVariableProviderCore>.hasVariables()

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(name: String, vararg names: String): Boolean =
        super<KoVariableProviderCore>.hasVariableWithName(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasPropertyWithName()"))
    override fun hasVariableWithName(names: Collection<String>): Boolean =
        super<KoVariableProviderCore>.hasVariableWithName(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(name: String, vararg names: String): Boolean =
        super<KoVariableProviderCore>.hasVariablesWithAllNames(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasPropertiesWithAllNames()"))
    override fun hasVariablesWithAllNames(names: Collection<String>): Boolean =
        super<KoVariableProviderCore>.hasVariablesWithAllNames(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasProperty()"))
    override fun hasVariable(predicate: (KoVariableDeclaration) -> Boolean): Boolean =
        super<KoVariableProviderCore>.hasVariable(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasAllProperties()"))
    override fun hasAllVariables(predicate: (KoVariableDeclaration) -> Boolean): Boolean =
        super<KoVariableProviderCore>.hasAllVariables(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("countClasses()"))
    override fun countLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Int =
        super<KoLocalClassProviderCore>.countLocalClasses(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClasses()"))
    override fun hasLocalClasses(): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClasses()

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(name: String, vararg names: String): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClassWithName(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClassWithName()"))
    override fun hasLocalClassWithName(names: Collection<String>): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClassWithName(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(name: String, vararg names: String): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClassesWithAllNames(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClassesWithAllNames()"))
    override fun hasLocalClassesWithAllNames(names: Collection<String>): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClassesWithAllNames(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasClass()"))
    override fun hasLocalClass(predicate: (KoClassDeclaration) -> Boolean): Boolean =
        super<KoLocalClassProviderCore>.hasLocalClass(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasAllClasses()"))
    override fun hasAllLocalClasses(predicate: (KoClassDeclaration) -> Boolean): Boolean =
        super<KoLocalClassProviderCore>.hasAllLocalClasses(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("countDeclarations()"))
    override fun countLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Int =
        super<KoLocalDeclarationProviderCore>.countLocalDeclarations(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasDeclarations()"))
    override fun hasLocalDeclarations(): Boolean =
        super<KoLocalDeclarationProviderCore>.hasLocalDeclarations()

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasDeclaration()"))
    override fun hasLocalDeclaration(predicate: (KoBaseDeclaration) -> Boolean): Boolean =
        super<KoLocalDeclarationProviderCore>.hasLocalDeclaration(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasAllDeclarations()"))
    override fun hasAllLocalDeclarations(predicate: (KoBaseDeclaration) -> Boolean): Boolean =
        super<KoLocalDeclarationProviderCore>.hasAllLocalDeclarations(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("countFunctions()"))
    override fun countLocalFunctions(predicate: (KoFunctionDeclaration) -> Boolean): Int =
        super<KoLocalFunctionProviderCore>.countLocalFunctions(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunctions()"))
    override fun hasLocalFunctions(): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunctions()

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(name: String, vararg names: String): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunctionWithName(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunctionWithName()"))
    override fun hasLocalFunctionWithName(names: Collection<String>): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunctionWithName(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(name: String, vararg names: String): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunctionsWithAllNames(name, *names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunctionsWithAllNames()"))
    override fun hasLocalFunctionsWithAllNames(names: Collection<String>): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunctionsWithAllNames(names)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasFunction()"))
    override fun hasLocalFunction(predicate: (KoFunctionDeclaration) -> Boolean): Boolean =
        super<KoLocalFunctionProviderCore>.hasLocalFunction(predicate)

    @Deprecated("Will be removed in version 0.20.0", replaceWith = ReplaceWith("hasAllFunctions()"))
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
