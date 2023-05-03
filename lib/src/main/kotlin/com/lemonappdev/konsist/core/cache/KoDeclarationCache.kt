package com.lemonappdev.konsist.core.cache

import com.lemonappdev.konsist.core.declaration.KoBaseDeclaration
import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

internal class KoDeclarationCache<T : KoBaseDeclaration> {
    private val elements = ConcurrentHashMap<KtElement, T>()

    private fun get(key: KtElement): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.name}" }
        return value
    }

    private fun set(key: KtElement, value: T) {
        elements[key] = value
    }

    private fun hasKey(ktElement: KtElement) = elements.containsKey(ktElement)

    fun getOrCreateInstance(ktElement: KtElement, value: (KtElement) -> T) = if (hasKey(ktElement)) {
        get(ktElement)
    } else {
        set(ktElement, value.invoke(ktElement))
        get(ktElement)
    }
}
