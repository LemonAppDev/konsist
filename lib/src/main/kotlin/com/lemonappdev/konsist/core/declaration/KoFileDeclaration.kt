package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.core.declaration.provider.KoClassProvider
import com.lemonappdev.konsist.core.declaration.provider.KoCompanionObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoDeclarationProvider
import com.lemonappdev.konsist.core.declaration.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.provider.KoInterfaceProvider
import com.lemonappdev.konsist.core.declaration.provider.KoObjectProvider
import com.lemonappdev.konsist.core.declaration.provider.KoPropertyProvider
import kotlin.reflect.KClass

interface KoFileDeclaration :
    KoNamedDeclaration,
    KoDeclarationProvider,
    KoClassProvider,
    KoInterfaceProvider,
    KoObjectProvider,
    KoCompanionObjectProvider,
    KoPropertyProvider,
    KoFunctionProvider {

    val imports: List<KoImportDeclaration>

    val annotations: List<KoAnnotationDeclaration>

    val packagee: List<KoPackageDeclaration>

    val typeAliases: List<KoTypeAliasDeclaration>

    fun hasAnnotations(vararg names: String): Boolean

    fun hasAnnotationsOf(vararg names: KClass<*>): Boolean

    fun hasPackage(name: String): Boolean

    fun hasImports(vararg names: String): Boolean

    fun hasTypeAliases(vararg names: String): Boolean

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
