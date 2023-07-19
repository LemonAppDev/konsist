package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.provider.KoParentDeclarationProvider
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoParentDeclarationProvider> {
    private val elements = ConcurrentHashMap<Pair<KtElement, KoParentDeclarationProvider?>, T>()

    private fun get(key: Pair<KtElement, KoParentDeclarationProvider?>): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.first.name}" }
        return value
    }

    private fun set(key: Pair<KtElement, KoParentDeclarationProvider?>, value: T) {
        elements[key] = value
    }

    private fun hasKey(key: Pair<KtElement, KoParentDeclarationProvider?>) = elements.containsKey(key)

    fun getOrCreateInstance(ktElement: KtElement, parentDeclaration: KoParentDeclarationProvider?, value: (KtElement) -> T): T {
        val cacheKey = ktElement to parentDeclaration

        return if (hasKey(cacheKey)) {
            get(cacheKey)
        } else {
            set(cacheKey, value.invoke(ktElement))
            get(cacheKey)
        }
    }
}
