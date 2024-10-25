package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoExternalDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoBaseProvider> {
    val elements = ConcurrentHashMap<Pair<KtElement, KoBaseProvider?>, T>()

    private fun get(key: Pair<KtElement, KoBaseProvider?>): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.first.name}" }
        return value
    }

    private fun set(
        key: Pair<KtElement, KoBaseProvider?>,
        value: T,
    ) {
        elements[key] = value
    }

    private fun hasKey(key: Pair<KtElement, KoBaseProvider?>) = elements.containsKey(key)

    fun getOrCreateInstance(
        ktElement: KtElement,
        containingDeclaration: KoBaseDeclaration,
        value: (KtElement) -> T,
    ): T {
        val cacheKey = ktElement to containingDeclaration

        return if (hasKey(cacheKey)) {
            get(cacheKey)
        } else {
            set(cacheKey, value.invoke(ktElement))
            get(cacheKey)
        }
    }
}

internal object KoExternalDeclarationCache {
    val elements = ConcurrentHashMap<String, KoExternalDeclaration>()

    private fun get(key: String): KoExternalDeclaration {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: $key" }
        return value
    }

    private fun set(
        key: String,
        value: KoExternalDeclaration,
    ) {
        elements[key] = value
    }

    private fun hasKey(key: String) = elements.containsKey(key)

    fun getOrCreateInstance(
        key: String,
        ktElement: KtElement,
        value: (ktElement: KtElement) -> KoExternalDeclaration,
    ): KoExternalDeclaration =
        if (hasKey(key)) {
            get(key)
        } else {
            set(key, value.invoke(ktElement))
            get(key)
        }
}
