package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProviderUtil
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider
import org.jetbrains.kotlin.psi.KtClassOrObject

abstract class KoComplexDeclarationImpl(
    private val ktClassOrObject: KtClassOrObject,
) : KoDeclarationImpl(ktClassOrObject),
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

    fun representsType(name: String) =
        name == this.name || name == fullyQualifiedName

    inline fun <reified T>representsTypeOf() = T::class.qualifiedName == fullyQualifiedName

    override fun declarations(
        modifiers: List<KoModifier>,
        includeNested: Boolean,
        includeLocal: Boolean,
    ): Sequence<KoDeclarationImpl> = KoDeclarationProviderUtil.getKoDeclarations(ktClassOrObject, modifiers, includeNested, includeLocal)
}
