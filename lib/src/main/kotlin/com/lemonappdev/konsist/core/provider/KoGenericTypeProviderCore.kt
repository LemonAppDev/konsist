package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.core.declaration.type.KoGenericTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoGenericTypeProviderCore :
    KoGenericTypeProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    val ktUserType: KtUserType?

    override val genericType: KoSourceDeclaration?
        get() {
            val hasKtTypeArgumentListChild = ktUserType
                ?.children
                ?.filterIsInstance<KtTypeArgumentList>()
                ?.isNotEmpty()

            if (hasKtTypeArgumentListChild == false) {
                return null
            } else {
                val ktNameReferenceExpression =
                    ktUserType
                        ?.children
                        ?.filterIsInstance<KtNameReferenceExpression>()
                        ?.firstOrNull()

                return if (ktNameReferenceExpression == null) {
                    null
                } else {
                    val type = TypeUtil.getBasicType(
                        listOf(ktNameReferenceExpression),
                        ktNameReferenceExpression.isExtensionDeclaration(),
                        this.castToKoBaseDeclaration(),
                        containingFile,
                    )

                    if (type is KoGenericTypeDeclarationCore) {
                        type.sourceDeclaration
                    } else {
                        type
                    }
                }
            }
        }

    override fun hasGenericType(predicate: (KoSourceDeclaration) -> Boolean): Boolean =
        genericType?.let { predicate(it) } ?: false

    override fun hasGenericTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(genericType, kClass)
}
