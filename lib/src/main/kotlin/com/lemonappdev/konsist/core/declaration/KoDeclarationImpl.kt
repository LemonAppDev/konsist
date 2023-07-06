package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.declaration.KoAnnotationDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoDeclaration
import com.lemonappdev.konsist.api.provider.KoParentProvider
import com.lemonappdev.konsist.core.exception.KoInternalException
import com.lemonappdev.konsist.core.provider.KoAnnotationDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoModifierProviderCore
import com.lemonappdev.konsist.core.provider.KoPackageDeclarationProviderCore
import com.lemonappdev.konsist.core.util.LocationUtil
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.psi.psiUtil.isPublic
import org.jetbrains.kotlin.psi.psiUtil.isTopLevelKtOrJavaMember
import kotlin.reflect.KClass

internal abstract class KoDeclarationImpl(
    override val ktTypeParameterListOwner: KtTypeParameterListOwner,
    val parentDeclaration: KoParentProvider?,
) :
    KoBaseDeclarationImpl(ktTypeParameterListOwner),
    KoAnnotationDeclarationProviderCore,
    KoPackageDeclarationProviderCore,
    KoModifierProviderCore,
    KoDeclaration {

    override val fullyQualifiedName: String by lazy {
        if (ktTypeParameterListOwner.fqName != null) {
            ktTypeParameterListOwner.fqName.toString()
        } else {
            ""
        }
    }

    override fun isTopLevel(): Boolean = ktTypeParameterListOwner.isTopLevelKtOrJavaMember()

}
