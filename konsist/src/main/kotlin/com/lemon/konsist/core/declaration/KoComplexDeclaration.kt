package com.lemon.konsist.core.declaration

import com.lemon.konsist.core.util.KoDeclarationUtil
import org.jetbrains.kotlin.psi.KtClassOrObject
import kotlin.reflect.KClass

open class KoComplexDeclaration(
    private val ktClassOrObject: KtClassOrObject,
) : KoDeclaration(ktClassOrObject), KoDeclarationProvider {

    fun representsType(kClass: KClass<*>) = kClass.qualifiedName == fullyQualifiedName

    override fun declarations(includeNested: Boolean, includeLocal: Boolean): List<KoDeclaration> =
        KoDeclarationUtil.getKoDeclarations(ktClassOrObject, includeNested, includeLocal)
}
