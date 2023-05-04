package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.core.declaration.KoBaseDeclarationImpl
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoBaseDeclarationImpl> {
    private val elements = ConcurrentHashMap<Pair<KtElement, KoBaseDeclarationImpl?>, T>()

    private fun get(key: Pair<KtElement, KoBaseDeclarationImpl?>): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.first.name}" }
        return value
    }

    private fun set(key: Pair<KtElement, KoBaseDeclarationImpl?>, value: T) {
        elements[key] = value
    }

    private fun hasKey(key: Pair<KtElement, KoBaseDeclarationImpl?>) = elements.containsKey(key)

    fun getOrCreateInstance(ktElement: KtElement, parent: KoBaseDeclarationImpl?, value: (KtElement) -> T): T {
        val cacheKey = ktElement to parent

        return if (hasKey(cacheKey)) {
            get(cacheKey)
        } else {
            set(cacheKey, value.invoke(ktElement))
            get(cacheKey)
        }
    }
}
