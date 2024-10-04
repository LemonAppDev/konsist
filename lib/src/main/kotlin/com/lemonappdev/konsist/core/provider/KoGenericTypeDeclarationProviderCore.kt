package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFullyQualifiedNameProvider
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.core.declaration.KoTypeArgumentDeclarationCore
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import com.lemonappdev.konsist.core.util.TypeUtil
import com.lemonappdev.konsist.core.util.TypeUtil.hasTypeOf
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtProjectionKind
import org.jetbrains.kotlin.psi.KtTypeArgumentList
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.psiUtil.isExtensionDeclaration
import kotlin.reflect.KClass

internal interface KoGenericTypeDeclarationProviderCore :
    KoGenericTypeDeclarationProvider,
    KoBaseProviderCore,
    KoContainingFileProviderCore {
    val ktUserType: KtUserType

    override val type: KoBaseTypeDeclaration
        get() {
            val ktNameReferenceExpression =
                ktUserType
                    .children
                    .filterIsInstance<KtNameReferenceExpression>()
                    .firstOrNull()

            require(ktNameReferenceExpression != null) { "Generic type cannot be null." }

            return TypeUtil.getBasicType(
                listOf(ktNameReferenceExpression),
                ktNameReferenceExpression.isExtensionDeclaration(),
                this.castToKoBaseDeclaration(),
                containingFile
            ) ?: throw (Exception("Generic type cannot be null."))
        }

    override fun hasType(predicate: (KoBaseTypeDeclaration) -> Boolean): Boolean = predicate(type)

    override fun hasTypeOf(kClass: KClass<*>): Boolean = hasTypeOf(type, kClass)
}
