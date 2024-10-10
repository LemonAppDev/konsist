package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import org.jetbrains.kotlin.com.intellij.psi.impl.source.tree.LeafElement
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.psiUtil.allChildren

internal interface KoModifierProviderCore :
    KoModifierProvider,
    KoBaseProviderCore {
    val ktModifierListOwner: KtModifierListOwner?

    override val modifiers: List<KoModifier>
        get() =
            ktModifierListOwner
                ?.modifierList
                ?.allChildren
                ?.toList()
                ?.filterIsInstance<LeafElement>()
                ?.filter { it.elementType is KtModifierKeywordToken }
                ?.map { it.elementType }
                ?.map {
                    KoModifier
                        .values()
                        .firstOrNull { modifier -> modifier.type == it.toString() }
                        ?: throw KoInternalException("Modifier not found: $it")
                }.orEmpty()

    override val numModifiers: Int
        get() = modifiers.size

    override fun hasModifiers(): Boolean = modifiers.isNotEmpty()

    override fun hasModifier(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasModifier(listOf(modifier, *modifiers))

    override fun hasModifier(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> modifiers.any { this.modifiers.any { modifier -> modifier == it } }
        }

    override fun hasAllModifiers(
        modifier: KoModifier,
        vararg modifiers: KoModifier,
    ): Boolean = hasAllModifiers(listOf(modifier, *modifiers))

    override fun hasAllModifiers(modifiers: Collection<KoModifier>): Boolean =
        when {
            modifiers.isEmpty() -> hasModifiers()
            else -> this.modifiers.containsAll(modifiers)
        }
}
