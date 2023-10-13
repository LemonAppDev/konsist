package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoBaseProvider> {
    private val elements = ConcurrentHashMap<Pair<KtElement, KoBaseProvider?>, T>()

    private fun get(key: Pair<KtElement, KoBaseProvider?>): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.first.name}" }
        return value
    }

    private fun set(key: Pair<KtElement, KoBaseProvider?>, value: T) {
        elements[key] = value
    }

    private fun hasKey(key: Pair<KtElement, KoBaseProvider?>) = elements.containsKey(key)

    fun getOrCreateInstance(ktElement: KtElement, containingDeclaration: KoBaseDeclaration, value: (KtElement) -> T): T {
        val cacheKey = ktElement to containingDeclaration

        return if (hasKey(cacheKey)) {
            get(cacheKey)
        } else {
            set(cacheKey, value.invoke(ktElement))
            get(cacheKey)
        }
    }
}
