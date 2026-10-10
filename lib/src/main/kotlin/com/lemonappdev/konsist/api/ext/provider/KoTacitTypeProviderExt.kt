import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider

/**
 * Determines whether the declaration has a tacit type of type [T].
 *
 * @return `true` if the declaration has a tacit type matching [T], `false` otherwise.
 */
inline fun <reified T> KoTacitTypeProvider.hasTacitTypeOf(): Boolean = hasTacitTypeOf(T::class)
