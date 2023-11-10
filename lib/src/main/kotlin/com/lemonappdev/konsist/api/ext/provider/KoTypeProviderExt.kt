import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider

/**
 * Determines whatever declaration has type.
 *
 * @return `true` if the declaration has type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoNonNullableTypeProvider.hasTypeOf(): Boolean = hasTypeOf(T::class)
