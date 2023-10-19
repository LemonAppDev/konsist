package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.api.declaration.KoExternalParentDeclaration
import com.lemonappdev.konsist.api.provider.KoBaseProvider
import com.lemonappdev.konsist.api.provider.KoContainingDeclarationProvider
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
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

    fun getOrCreateInstance(
        ktElement: KtElement,
        containingDeclaration: KoContainingDeclarationProvider,
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

internal object KoExternalParentCache {
    private val elements = ConcurrentHashMap<String, KoExternalParentDeclaration>()

    private fun get(key: String): KoExternalParentDeclaration {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: $key" }
        return value
    }

    private fun set(key: String, value: KoExternalParentDeclaration) {
        elements[key] = value
    }

    private fun hasKey(key: String) = elements.containsKey(key)

    fun getOrCreateInstance(
        key: String,
        ktSuperTypeListEntry: KtSuperTypeListEntry,
        value: (ktSuperTypeListEntry: KtSuperTypeListEntry) -> KoExternalParentDeclaration,
    ): KoExternalParentDeclaration {
        return if (hasKey(key)) {
            get(key)
        } else {
            set(key, value.invoke(ktSuperTypeListEntry))
            get(key)
        }
    }
}
