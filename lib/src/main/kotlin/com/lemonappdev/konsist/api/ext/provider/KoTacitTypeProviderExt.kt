import com.lemonappdev.konsist.api.provider.KoTacitTypeProvider

/**
 * Determines whatever declaration has tacit type.
 *
 * @return `true` if the declaration has tacit type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoTacitTypeProvider.hasTacitTypeOf(): Boolean = hasTacitTypeOf(T::class)
