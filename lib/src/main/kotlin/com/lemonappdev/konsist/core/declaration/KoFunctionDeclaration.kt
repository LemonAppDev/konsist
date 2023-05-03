package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.declaration.provider.KoLocalClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoLocalPropertyProvider
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.KtProperty
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.psiUtil.getTextWithLocation
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import org.jetbrains.kotlin.utils.addToStdlib.firstIsInstanceOrNull

@Suppress("detekt.TooManyFunctions")
class KoFunctionDeclaration private constructor(private val ktFunction: KtFunction) :
    KoParametrizedDeclaration(ktFunction),
    KoLocalClassProvider,
    KoLocalFunctionProvider,
    KoLocalPropertyProvider {

    private val localDeclarations by lazy {
        val psiChildren = ktFunction
            .bodyBlockExpression
            ?.children
            ?.asSequence()
            ?: emptySequence()

        psiChildren.mapNotNull {
            if (it is KtClass && !it.isInterface()) {
                KoClassDeclaration.getInstance(it)
            } else if (it is KtFunction) {
                getInstance(it)
            } else if (it is KtProperty) {
                KoPropertyDeclaration.getInstance(it)
            } else {
                throw UnsupportedOperationException("Unknown local declaration type: ${it.getTextWithLocation()}")
            }
        }
    }

    val returnType by lazy {
        val type = ktFunction
            .children
            .firstIsInstanceOrNull<KtTypeReference>()

        type?.let { KoTypeDeclaration.getInstance(type) }
    }

    fun hasOperatorModifier() = hasModifiers(KoModifier.OPERATOR)

    fun hasInlineModifier() = hasModifiers(KoModifier.INLINE)

    fun hasTailrecModifier() = hasModifiers(KoModifier.TAILREC)

    fun hasInfixModifier() = hasModifiers(KoModifier.INFIX)

    fun hasExternalModifier() = hasModifiers(KoModifier.EXTERNAL)

    fun hasSuspendModifier() = hasModifiers(KoModifier.SUSPEND)

    fun hasOpenModifier() = hasModifiers(KoModifier.OPEN)

    fun hasOverrideModifier() = hasModifiers(KoModifier.OVERRIDE)

    fun hasFinalModifier() = hasModifiers(KoModifier.FINAL)

    fun hasAbstractModifier() = hasModifiers(KoModifier.ABSTRACT)

    fun hasActualModifier() = hasModifiers(KoModifier.ACTUAL)

    fun hasExpectModifier() = hasModifiers(KoModifier.EXPECT)

    fun isExtension() = ktFunction.isExtensionDeclaration()

    fun hasReturnType() = ktFunction.hasDeclaredReturnType()

    override fun localDeclarations(): Sequence<KoDeclaration> = localDeclarations

    companion object {
        private val cache = KoDeclarationCache<KoFunctionDeclaration>()

        fun getInstance(ktFunction: KtFunction) = cache.getOrCreateInstance(ktFunction) { KoFunctionDeclaration(ktFunction) }
    }
}
