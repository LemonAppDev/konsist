package com.lemonappdev.konsist.core.provider

import com.lemonappdev.konsist.api.declaration.KoInitBlockDeclaration
import com.lemonappdev.konsist.api.provider.KoInitBlockProvider
import com.lemonappdev.konsist.core.declaration.KoInitBlockDeclarationImpl
import org.jetbrains.kotlin.psi.KtClass

internal interface KoInitBlockProviderCore : KoInitBlockProvider, KoParentProviderCore {
    val ktClass: KtClass

    override val initBlocks: List<KoInitBlockDeclaration>?
        get() {
            val anonymousInitializers = ktClass
                .body
                ?.anonymousInitializers

            return if (anonymousInitializers?.isEmpty() == true) {
                null
            } else {
                anonymousInitializers?.map { init -> KoInitBlockDeclarationImpl.getInstance(init, this) }
            }
        }

    override val numInitBlocks: Int
        get() = initBlocks?.size ?: 0

    override fun hasInitBlocks(): Boolean = initBlocks != null
}
