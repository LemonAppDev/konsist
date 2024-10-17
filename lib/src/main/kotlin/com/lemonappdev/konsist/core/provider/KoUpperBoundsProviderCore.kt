package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoSourceDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.ext.list.sourceDeclarations
import com.lemonappdev.konsist.api.provider.KoUpperBoundsProvider
import com.lemonappdev.konsist.core.declaration.type.KoTypeDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtTypeReference

internal interface KoUpperBoundsProviderCore :
    KoUpperBoundsProvider,
    KoBaseProviderCore {
    val ktTypeReferences: List<KtTypeReference>

    override val upperBounds: List<KoTypeDeclaration>
        get() =
            ktTypeReferences
                .map { typeReference -> KoTypeDeclarationCore.getInstance(typeReference, this.castToKoBaseDeclaration()) }

    override val numUpperBounds: Int
        get() = upperBounds.size

    override fun countUpperBounds(predicate: (KoTypeDeclaration) -> Boolean): Int = upperBounds.count { predicate(it) }

    override fun hasUpperBounds(): Boolean = upperBounds.isNotEmpty()

    override fun hasUpperBoundWithName(
        name: String,
        vararg names: String,
    ): Boolean = hasUpperBoundWithName(listOf(name, *names))

    override fun hasUpperBoundWithName(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasUpperBounds()
            else ->
                names.any {
                    upperBounds.any { parameter -> it == parameter.name }
                }
        }

    override fun hasUpperBoundsWithAllNames(
        name: String,
        vararg names: String,
    ): Boolean = hasUpperBoundsWithAllNames(listOf(name, *names))

    override fun hasUpperBoundsWithAllNames(names: Collection<String>): Boolean =
        when {
            names.isEmpty() -> hasUpperBounds()
            else ->
                names.all {
                    upperBounds.any { parameter -> it == parameter.name }
                }
        }

    override fun hasUpperBound(predicate: (KoTypeDeclaration) -> Boolean): Boolean = upperBounds.any(predicate)

    override fun hasAllUpperBounds(predicate: (KoTypeDeclaration) -> Boolean): Boolean = upperBounds.all(predicate)
}
