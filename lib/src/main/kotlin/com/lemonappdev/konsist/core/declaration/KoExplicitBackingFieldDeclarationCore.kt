package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExplicitBackingFieldDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTacitTypeProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoValueProviderCore
import com.lemonappdev.konsist.core.util.TypeUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtBackingField
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtExpression
import kotlin.reflect.KClass
import kotlin.reflect.KType

internal class KoExplicitBackingFieldDeclarationCore private constructor(
    private val ktBackingField: KtBackingField,
    override val containingDeclaration: KoBaseDeclaration,
) : KoExplicitBackingFieldDeclaration,
    KoBaseProviderCore,
    KoContainingDeclarationProviderCore,
    KoContainingFileProviderCore,
    KoLocationProviderCore,
    KoModuleProviderCore,
    KoPathProviderCore,
    KoSourceSetProviderCore,
    KoTacitTypeProviderCore,
    KoTextProviderCore,
    KoValueProviderCore {
    override val psiElement: PsiElement
        get() = ktBackingField

    override val ktElement: KtElement
        get() = ktBackingField

    override val ktExpression: KtExpression?
        get() = ktBackingField.initializer

    /*
    KtBackingField.typeReference is not available in Kotlin compiler prior to 2.4 (Konsist supports older compiler
    at runtime), so returnTypeReference is used.
     */
    override val type: KoTypeDeclaration?
        get() =
            ktBackingField
                .returnTypeReference
                ?.let { KoTypeDeclarationCore.getInstance(it, this) }

    override fun hasType(predicate: ((KoTypeDeclaration) -> Boolean)?): Boolean =
        when (predicate) {
            null -> type != null
            else -> type?.let { predicate(it) } ?: false
        }

    override fun hasTypeOf(kClass: KClass<*>): Boolean = TypeUtil.hasTypeOf(type, kClass)

    override fun hasTypeOf(kType: KType): Boolean = TypeUtil.hasTypeOf(type, kType)

    override fun toString(): String = locationWithText

    internal companion object {
        private val cache: KoDeclarationCache<KoExplicitBackingFieldDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktBackingField: KtBackingField,
            containingDeclaration: KoBaseDeclaration,
        ): KoExplicitBackingFieldDeclaration =
            cache.getOrCreateInstance(ktBackingField, containingDeclaration) {
                KoExplicitBackingFieldDeclarationCore(ktBackingField, containingDeclaration)
            }
    }
}
