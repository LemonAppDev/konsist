package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic

internal interface KoModifierProviderCore: KoModifierProvider {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val modifiers: List<KoModifier>
        get() = ktTypeParameterListOwner
            .modifierList
            ?.text
            ?.split("\n")
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
                    .values()
                    .firstOrNull { modifier -> modifier.type == it }
                    ?: throw KoInternalException("Modifier not found: $it")
            }
            ?.toList()
            ?: emptyList()

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }

    override fun hasPublicModifier(): Boolean = hasModifiers(KoModifier.PUBLIC)

    override fun isPublicOrDefault(): Boolean = ktTypeParameterListOwner.isPublic

    override fun hasPrivateModifier(): Boolean = hasModifiers(KoModifier.PRIVATE)

    override fun hasProtectedModifier(): Boolean = hasModifiers(KoModifier.PROTECTED)

    override fun hasInternalModifier(): Boolean = hasModifiers(KoModifier.INTERNAL)
}
