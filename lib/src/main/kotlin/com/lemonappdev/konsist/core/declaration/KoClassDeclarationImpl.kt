package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.api.declaration.KoConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.declaration.KoPrimaryConstructorDeclaration
import com.lemonappdev.konsist.api.declaration.KoSecondaryConstructorDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.util.TagUtil
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry
import org.jetbrains.kotlin.psi.KtSuperTypeEntry
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry

internal class KoClassDeclarationImpl private constructor(private val ktClass: KtClass, parentDeclaration: KoParentProvider?) :
    KoComplexDeclarationImpl(ktClass, parentDeclaration),
    KoClassDeclaration,
    KoModifierProviderCore {
    override val parents: List<KoParentDeclaration> by lazy {
        ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeListEntry>()
            ?.map { KoParentDeclarationImpl.getInstance(it, this) } ?: emptyList()
    }

    override val parentInterfaces: List<KoParentDeclaration> by lazy {
        val interfaces = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeEntry>() ?: emptyList()

        val delegations = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtDelegatedSuperTypeEntry>() ?: emptyList()

        val all = interfaces + delegations
        all.map { KoParentDeclarationImpl.getInstance(it, this) }
    }

    override val parentClass: KoParentDeclaration? by lazy {
        val parentClass = ktClass
            .getSuperTypeList()
            ?.children
            ?.filterIsInstance<KtSuperTypeCallEntry>()
            ?.first()

        parentClass?.let { KoParentDeclarationImpl.getInstance(it, this) }
    }

    override val primaryConstructor: KoPrimaryConstructorDeclaration? by lazy {
        val localPrimaryConstructor = ktClass.primaryConstructor ?: return@lazy null

        KoPrimaryConstructorDeclarationImpl.getInstance(localPrimaryConstructor, this)
    }

    override val secondaryConstructors: List<KoSecondaryConstructorDeclaration> by lazy {
        ktClass
            .secondaryConstructors
            .map { KoSecondaryConstructorDeclarationImpl.getInstance(it, this) }
    }

    override val allConstructors: List<KoConstructorDeclaration> by lazy {
        listOfNotNull(primaryConstructor) + secondaryConstructors
    }

    override fun hasPrimaryConstructor(): Boolean = ktClass.hasExplicitPrimaryConstructor()

    override fun hasSecondaryConstructors(): Boolean = ktClass.hasSecondaryConstructors()

    override fun hasParentClass(name: String?): Boolean = when (name) {
        null -> parentClass != null
        else -> parentClass?.name == name
    }

    override fun hasParentInterfaces(vararg names: String): Boolean = when {
        names.isEmpty() -> parentInterfaces.isNotEmpty()
        else -> names.all {
            parentInterfaces.any { koParent -> it == koParent.name }
        }
    }

    override fun hasParents(vararg names: String): Boolean = when {
        names.isEmpty() -> hasParentClass() || hasParentInterfaces()
        else -> names.all { hasParentClass(it) || hasParentInterfaces(it) }
    }

    override fun hasValidParamTag(enabled: Boolean): Boolean = TagUtil.hasValidParamTag(enabled, primaryConstructor?.parameters, kDoc)

    override fun hasTest(testFileNameSuffix: String, moduleName: String?, sourceSetName: String?): Boolean = Konsist
        .scopeFromTest(moduleName, sourceSetName)
        .classes()
        .any { it.name == name + testFileNameSuffix }

    internal companion object {
        private val cache: KoDeclarationCache<KoClassDeclaration> = KoDeclarationCache()

        internal fun getInstance(ktClass: KtClass, parentDeclaration: KoParentProvider?): KoClassDeclaration =
            cache.getOrCreateInstance(ktClass, parentDeclaration) {
                KoClassDeclarationImpl(ktClass, parentDeclaration)
            }
    }
}
