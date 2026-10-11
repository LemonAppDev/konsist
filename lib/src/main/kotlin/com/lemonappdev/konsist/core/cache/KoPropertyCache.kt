package com.lemonappdev.konsist.core.cache

/**
 * Caches property values of declarations in private fields.
 *
 * Declarations are created for elements of every parsed file and stay in memory for the whole test run.
 * Lazy delegate allocates ~40 bytes for every property of every declaration (delegate and initializer lambda), even
 * when the property is never read, while the cache field takes 4 bytes:
 *
 * ```kotlin
 * @Volatile
 * private var cachedName: Any? = KoPropertyCache.NOT_COMPUTED
 *
 * override val name: String
 *     get() = KoPropertyCache.getOrCompute(cachedName, { cachedName = it }) { ktClass.name }
 * ```
 *
 * The value can be computed more than once when multiple threads read the property at the same time (the last value
 * is cached), so computed values have to be equal, e.g. declarations have to be obtained from declaration cache.
 */
internal object KoPropertyCache {
    /**
     * Value of the cache field before the property value is computed.
     */
    val NOT_COMPUTED: Any = Any()

    @Suppress("UNCHECKED_CAST")
    inline fun <T> getOrCompute(
        cachedValue: Any?,
        cache: (T) -> Unit,
        compute: () -> T,
    ): T =
        if (cachedValue === NOT_COMPUTED) {
            compute().also(cache)
        } else {
            cachedValue as T
        }
}
