package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.core.declaration.KoInitBlockDeclarationCore
import com.lemonappdev.konsist.core.ext.castToKoBaseDeclaration
import org.jetbrains.kotlin.psi.KtClassOrObject

internal interface KoInitBlockProviderCore :
    KoInitBlockProvider,
    KoContainingDeclarationProviderCore,
    KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val initBlocks: List<KoInitBlockDeclaration>
        get() {
            val anonymousInitializers =
                ktClassOrObject
                    .body
                    ?.anonymousInitializers

            return if (anonymousInitializers?.isEmpty() == true) {
                emptyList()
            } else {
                anonymousInitializers
                    ?.map { init -> KoInitBlockDeclarationCore.getInstance(init, this.castToKoBaseDeclaration()) }
                    .orEmpty()
            }
        }

    override val numInitBlocks: Int
        get() = initBlocks.size

    @Deprecated("Will be removed in v0.16.0", replaceWith = ReplaceWith("hasInitBlocks()"))
    override val hasInitBlocks: Boolean
        get() = initBlocks.isNotEmpty()

    override fun countInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Int = initBlocks.count { predicate(it) }

    override fun hasInitBlocks(): Boolean = initBlocks.isNotEmpty()

    override fun hasInitBlock(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean = initBlocks.any(predicate)

    override fun hasAllInitBlocks(predicate: (KoInitBlockDeclaration) -> Boolean): Boolean = initBlocks.all(predicate)
}
