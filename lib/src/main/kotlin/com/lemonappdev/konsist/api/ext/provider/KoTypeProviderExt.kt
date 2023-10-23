import com.lemonappdev.konsist.api.provider.KoTypeProvider

/**
 * Determines whatever declaration has type.
 *
 * @return `true` if the declaration has type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoTypeProvider.hasTypeOf(): Boolean = hasTypeOf(T::class)
