package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.const.KoModifier
import com.lemon.konsist.core.declaration.provider.KoClassProvider
import com.lemon.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemon.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemon.konsist.core.declaration.provider.KoFunctionProvider
import com.lemon.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemon.konsist.core.declaration.provider.KoObjectProvider
import com.lemon.konsist.core.declaration.provider.KoPropertyProvider
import org.jetbrains.kotlin.psi.KtClassOrObject

abstract class KoComplexDeclaration(
    private val ktClassOrObject: KtClassOrObject,
) : KoDeclaration(ktClassOrObject),
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

    fun representsType(name: String) = name == fullyQualifiedName.substringAfterLast(".")

    inline fun <reified T>representsType() = T::class.qualifiedName == fullyQualifiedName

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoDeclaration> = KoDeclarationProviderUtil.getKoDeclarations(ktClassOrObject, modifiers, includeNested, includeLocal)
}
