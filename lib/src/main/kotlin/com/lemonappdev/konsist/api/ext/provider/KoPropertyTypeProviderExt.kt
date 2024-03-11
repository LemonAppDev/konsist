import com.lemonappdev.konsist.api.provider.KoPropertyTypeProvider

/**
 * Whether declaration has type.
 *
 * @return `true` if the declaration has type with the specified KClass name, `false` otherwise.
 */
inline fun <reified T> KoPropertyTypeProvider.hasTypeOf(): Boolean = hasTypeOf(T::class)
