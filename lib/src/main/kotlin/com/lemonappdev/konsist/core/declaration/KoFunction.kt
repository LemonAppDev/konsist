package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.const.KoModifier
import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.hasActualModifier
import org.jetbrains.kotlin.psi.psiUtil.hasExpectModifier
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

@Suppress("detekt.TooManyFunctions")
class KoFunction private constructor(private val ktFunction: KtFunction) :
    KoParametrizedDeclaration(ktFunction),
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider {

    private val localDeclarations by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.toList()
            ?: emptyList()

        psiChildren.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClass.getInstance(it)
            } else if (it is KtFunction) {
                getInstance(it)
            } else if (it is KtProperty) {
                KoProperty.getInstance(it)
            } else {
                throw KoInternalException("Unknown local declaration type: ${it.getTextWithLocation()}")
            }
        }
    }

    val explicitReturnType by lazy {
        val type = ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoType.getInstance(type) }
    }

    fun hasOperatorModifier() = modifiers.contains(KoModifier.OPERATOR)

    fun hasInlineModifier() = modifiers.contains(KoModifier.INLINE)

    fun hasTailrecModifier() = modifiers.contains(KoModifier.TAILREC)

    fun hasInfixModifier() = modifiers.contains(KoModifier.INFIX)

    fun hasExternalModifier() = modifiers.contains(KoModifier.EXTERNAL)

    fun hasSuspendModifier() = modifiers.contains(KoModifier.SUSPEND)

    fun hasOpenModifier() = modifiers.contains(KoModifier.OPEN)

    fun hasOverrideModifier() = modifiers.contains(KoModifier.OVERRIDE)

    fun hasFinalModifier() = modifiers.contains(KoModifier.FINAL)

    fun hasAbstractModifier() = modifiers.contains(KoModifier.ABSTRACT)

    fun hasActualModifier() = ktFunction.hasActualModifier()

    fun hasExpectModifier() = ktFunction.hasExpectModifier()

    fun isExtension() = ktFunction.isExtensionDeclaration()

    fun hasExplicitReturnType() = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): List<KoDeclaration> = localDeclarations

    companion object {
        private val cache = KoDeclarationCache<KoFunction>()

        fun getInstance(ktFunction: KtFunction) = cache.getOrCreateInstance(ktFunction) { KoFunction(ktFunction) }
    }
}
