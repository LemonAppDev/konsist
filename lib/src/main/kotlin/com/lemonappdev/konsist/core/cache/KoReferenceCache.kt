package com.lemonappdev.konsist.core.cache

import java.lang.ref.Reference
import java.util.concurrent.ConcurrentHashMap

/**
 * Caches values using soft or weak references, so cached values can be released by the garbage collector.
 *
 * Value is not released while it is used (referenced strongly), so all threads get the same instance for the given
 * key as long as it is used. Released value is created again when needed.
 */
internal class KoReferenceCache<T : Any>(
    private val createReference: (T) -> Reference<T>,
) {
    private val elements = ConcurrentHashMap<String, Reference<T>>()

    fun get(key: String): T? = elements[key]?.get()

    fun getOrCreateInstance(
        key: String,
        value: () -> T,
    ): T = get(key) ?: putIfAbsent(key, value())

    /*
     * Value created by another thread in the meantime is returned, so all threads get the same instance.
     */
    private fun putIfAbsent(
        key: String,
        value: T,
    ): T {
        var cachedValue = value

        elements.compute(key) { _, reference ->
            reference?.get()?.let {
                cachedValue = it
                reference
            } ?: createReference(value)
        }

        return cachedValue
    }
}
