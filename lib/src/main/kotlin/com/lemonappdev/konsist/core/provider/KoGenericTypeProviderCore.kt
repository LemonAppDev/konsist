package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoGenericTypeProviderCore :
    KoGenericTypeProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    val ktUserType: KtUserType?

    override val genericType: KoSourceDeclaration
        get() {
            val ktNameReferenceExpression =
                ktUserType
                    ?.children
                    ?.filterIsInstance<KtNameReferenceExpression>()
                    ?.firstOrNull()

            require(ktNameReferenceExpression != null) { "Generic type cannot be null." }

            return TypeUtil.getBasicType(
                listOf(ktNameReferenceExpression),
                ktNameReferenceExpression.isExtensionDeclaration(),
                this.castToKoBaseDeclaration(),
                containingFile,
            ) ?: throw (Exception("Generic type cannot be null."))
        }

    override fun hasGenericType(predicate: (KoSourceDeclaration) -> Boolean): Boolean = predicate(genericType)

    override fun hasGenericTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(genericType, kClass)
}
