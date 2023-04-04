package com.lemon.konsist.core.declaration

import org.jetbrains.kotlin.psi.KtElement
import java.util.concurrent.ConcurrentHashMap

open class KoDeclarationCache<T : KoBaseDeclaration> {

    private val elements = ConcurrentHashMap<KtElement, T>()

    fun get(key: KtElement): T {
        var value = elements[key]
        value = requireNotNull(value) { "Cache doesn't allow to null value of key: ${key.name}" }
        return value
    }

    fun set(key: KtElement, value: T) {
        elements[key] = value
    }

    fun hasKey(ktElement: KtElement) = elements.containsKey(ktElement)
}
