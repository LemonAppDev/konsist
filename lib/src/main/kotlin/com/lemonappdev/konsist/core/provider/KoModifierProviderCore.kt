package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic

@Suppress("detekt.TooManyFunctions")
internal interface KoModifierProviderCore : KoModifierProvider, KoBaseProviderCore {
    val ktTypeParameterListOwner: KtTypeParameterListOwner

    override val modifiers: Sequence<KoModifier>
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
            ?.asSequence()
            ?: emptySequence()

    override val numModifiers: Int
        get() = modifiers.toList().size

    override fun hasModifiers(vararg koModifiers: KoModifier): Boolean = when {
        koModifiers.isEmpty() -> modifiers.toList().isNotEmpty()
        else -> modifiers.toList().containsAll(koModifiers.toList())
    }

    override fun hasPublicModifier(): Boolean = hasModifiers(KoModifier.PUBLIC)

    override fun isPublicOrDefault(): Boolean = ktTypeParameterListOwner.isPublic

    override fun hasPrivateModifier(): Boolean = hasModifiers(KoModifier.PRIVATE)

    override fun hasProtectedModifier(): Boolean = hasModifiers(KoModifier.PROTECTED)

    override fun hasInternalModifier(): Boolean = hasModifiers(KoModifier.INTERNAL)

    override fun hasEnumModifier(): Boolean = hasModifiers(KoModifier.ENUM)

    override fun hasSealedModifier(): Boolean = hasModifiers(KoModifier.SEALED)

    override fun hasInnerModifier(): Boolean = hasModifiers(KoModifier.INNER)

    override fun hasValueModifier(): Boolean = hasModifiers(KoModifier.VALUE)

    override fun hasAnnotationModifier(): Boolean = hasModifiers(KoModifier.ANNOTATION)

    override fun hasDataModifier(): Boolean = hasModifiers(KoModifier.DATA)

    override fun hasActualModifier(): Boolean = hasModifiers(KoModifier.ACTUAL)

    override fun hasExpectModifier(): Boolean = hasModifiers(KoModifier.EXPECT)

    override fun hasAbstractModifier(): Boolean = hasModifiers(KoModifier.ABSTRACT)

    override fun hasOpenModifier(): Boolean = hasModifiers(KoModifier.OPEN)

    override fun hasFinalModifier(): Boolean = hasModifiers(KoModifier.FINAL)

    override fun hasVarargModifier(): Boolean = hasModifiers(KoModifier.VARARG)

    override fun hasNoInlineModifier(): Boolean = hasModifiers(KoModifier.NOINLINE)

    override fun hasCrossInlineModifier(): Boolean = hasModifiers(KoModifier.CROSSINLINE)

    override fun hasOperatorModifier(): Boolean = hasModifiers(KoModifier.OPERATOR)

    override fun hasInlineModifier(): Boolean = hasModifiers(KoModifier.INLINE)

    override fun hasTailrecModifier(): Boolean = hasModifiers(KoModifier.TAILREC)

    override fun hasInfixModifier(): Boolean = hasModifiers(KoModifier.INFIX)

    override fun hasExternalModifier(): Boolean = hasModifiers(KoModifier.EXTERNAL)

    override fun hasSuspendModifier(): Boolean = hasModifiers(KoModifier.SUSPEND)

    override fun hasOverrideModifier(): Boolean = hasModifiers(KoModifier.OVERRIDE)

    override fun hasFunModifier(): Boolean = hasModifiers(KoModifier.FUN)

    override fun hasLateinitModifier(): Boolean = hasModifiers(KoModifier.LATEINIT)

    override fun hasConstModifier(): Boolean = hasModifiers(KoModifier.CONST)

    override fun hasCompanionModifier(): Boolean = hasModifiers(KoModifier.COMPANION)
}
