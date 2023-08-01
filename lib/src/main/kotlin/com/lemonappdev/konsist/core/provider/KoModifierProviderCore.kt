package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic

@Suppress("detekt.TooManyFunctions")
internal interface KoModifierProviderCore : KoModifierProvider, KoBaseProviderCore {
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
                    .entries
                    .firstOrNull { modifier -> modifier.type == it }
                    ?: throw KoInternalException("Modifier not found: $it")
            }
            ?: emptyList()

    override val numModifiers: Int
        get() = modifiers.toList().size

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.isNotEmpty()
        else -> modifiers.containsAll(koModifiers.toList())
    }

    override val hasPublicModifier: Boolean
        get() = hasModifiers(KoModifier.PUBLIC)

    override val isPublicOrDefault: Boolean
        get() = ktTypeParameterListOwner.isPublic

    override val hasPrivateModifier: Boolean
        get() = hasModifiers(KoModifier.PRIVATE)

    override val hasProtectedModifier: Boolean
        get() = hasModifiers(KoModifier.PROTECTED)

    override val hasInternalModifier: Boolean
        get() = hasModifiers(KoModifier.INTERNAL)

    override val hasEnumModifier: Boolean
        get() = hasModifiers(KoModifier.ENUM)

    override val hasSealedModifier: Boolean
        get() = hasModifiers(KoModifier.SEALED)

    override val hasInnerModifier: Boolean
        get() = hasModifiers(KoModifier.INNER)

    override val hasValueModifier: Boolean
        get() = hasModifiers(KoModifier.VALUE)

    override val hasAnnotationModifier: Boolean
        get() = hasModifiers(KoModifier.ANNOTATION)

    override val hasDataModifier: Boolean
        get() = hasModifiers(KoModifier.DATA)

    override val hasActualModifier: Boolean
        get() = hasModifiers(KoModifier.ACTUAL)

    override val hasExpectModifier: Boolean
        get() = hasModifiers(KoModifier.EXPECT)

    override val hasAbstractModifier: Boolean
        get() = hasModifiers(KoModifier.ABSTRACT)

    override val hasOpenModifier: Boolean
        get() = hasModifiers(KoModifier.OPEN)

    override val hasFinalModifier: Boolean
        get() = hasModifiers(KoModifier.FINAL)

    override val hasVarargModifier: Boolean
        get() = hasModifiers(KoModifier.VARARG)

    override val hasNoInlineModifier: Boolean
        get() = hasModifiers(KoModifier.NOINLINE)

    override val hasCrossInlineModifier: Boolean
        get() = hasModifiers(KoModifier.CROSSINLINE)

    override val hasOperatorModifier: Boolean
        get() = hasModifiers(KoModifier.OPERATOR)

    override val hasInlineModifier: Boolean
        get() = hasModifiers(KoModifier.INLINE)

    override val hasTailrecModifier: Boolean
        get() = hasModifiers(KoModifier.TAILREC)

    override val hasInfixModifier: Boolean
        get() = hasModifiers(KoModifier.INFIX)

    override val hasExternalModifier: Boolean
        get() = hasModifiers(KoModifier.EXTERNAL)

    override val hasSuspendModifier: Boolean
        get() = hasModifiers(KoModifier.SUSPEND)

    override val hasOverrideModifier: Boolean
        get() = hasModifiers(KoModifier.OVERRIDE)

    override val hasFunModifier: Boolean
        get() = hasModifiers(KoModifier.FUN)

    override val hasLateinitModifier: Boolean
        get() = hasModifiers(KoModifier.LATEINIT)

    override val hasConstModifier: Boolean
        get() = hasModifiers(KoModifier.CONST)

    override val hasCompanionModifier: Boolean
        get() = hasModifiers(KoModifier.COMPANION)
}
