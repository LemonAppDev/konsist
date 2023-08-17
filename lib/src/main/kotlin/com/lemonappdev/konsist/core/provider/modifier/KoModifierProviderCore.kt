package com.lemonappdev.konsist.core.provider.modifier

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.util.EndOfLine
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal interface KoModifierProviderCore : KoModifierProvider, KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val modifiers: List<KoModifier>
        get() = ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split(EndOfLine.UNIX.value)
            ?.map { it.substringBefore("//") }
            ?.flatMap { it.split(" ") }
            ?.takeLastWhile {
                // We filter this way because this list contains modifiers and annotations,
                // and we need to exclude all annotations especially with blank spaces
                // e.g. @SampleAnnotation(parameter = "sample parameter")
                // and with angle brackets
                // e.g. @SampleAnnotation<String, Int>
                !it.contains('<') &&
                    !it.contains('>') &&
                    !it.contains(')') &&
                    !it.contains('@') &&
                    it.isNotBlank()
            }
            ?.map {
                KoModifier
                    .entries
                    .firstOrNull { modifier -> modifier.type == it }
                    ?: throw KoInternalException("Modifier not found: $it")
            }
            ?: emptyList()

    override val numModifiers: Int
        get() = modifiers.size

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }
}
