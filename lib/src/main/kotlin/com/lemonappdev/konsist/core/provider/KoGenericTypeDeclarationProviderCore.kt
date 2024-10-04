package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoGenericTypeDeclarationProviderCore :
    KoGenericTypeDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    val ktUserType: KtUserType?

    override val genericType: KoBaseTypeDeclaration
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
                containingFile
            ) ?: throw (Exception("Generic type cannot be null."))
        }

    override fun hasGenericType(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean = predicate(genericType)

    override fun hasGenericTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(genericType, kClass)
}
