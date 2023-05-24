package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.core.parent.KoParent
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoBaseDeclaration> {
    private val elements = ConcurrentHashMap<Pair<KtElement, KoParent?>, T>()

    private fun get(key: Pair<KtElement, KoParent?>): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.first.name}" }
        return value
    }

    private fun set(key: Pair<KtElement, KoParent?>, value: T) {
        elements[key] = value
    }

    private fun hasKey(key: Pair<KtElement, KoParent?>) = elements.containsKey(key)

    fun getOrCreateInstance(ktElement: KtElement, parent: KoParent, value: (KtElement) -> T): T {
        val cacheKey = ktElement to parent

        return if (hasKey(cacheKey)) {
            get(cacheKey)
        } else {
            set(cacheKey, value.invoke(ktElement))
            get(cacheKey)
        }
    }
}
