import com.lemonappdev.konsist.api.provider.KoNullableTypeProvider

/**
 * Determines whether the declaration has a type of type [T].
 *
 * @return `true` if the declaration has a type matching [T], `false` otherwise.
 */
inline fun <reified T> KoNullableTypeProvider.hasTypeOf(): Boolean = hasTypeOf(T::class)
