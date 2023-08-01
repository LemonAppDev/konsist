package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.core.declaration.KoInitBlockDeclarationImpl
import org.jetbrains.kotlin.psi.KtClassOrObject

internal interface KoInitBlockProviderCore : KoInitBlockProvider, KoParentProviderCore, KoBaseProviderCore {
    val ktClassOrObject: KtClassOrObject

    override val initBlocks: Sequence<KoInitBlockDeclaration>?
        get() {
            val anonymousInitializers = ktClassOrObject
                .body
                ?.anonymousInitializers

            return if (anonymousInitializers?.isEmpty() == true) {
                null
            } else {
                anonymousInitializers
                    ?.map { init -> KoInitBlockDeclarationImpl.getInstance(init, this) }
                    ?.asSequence()
            }
        }

    override val numInitBlocks: Int
        get() = initBlocks?.toList()?.size ?: 0

    override fun hasInitBlocks(): Boolean = initBlocks != null
}
