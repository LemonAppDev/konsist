import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import kotlin.reflect.typeOf

/**
 * Determines whether the declaration has a type of type [T], including its type arguments.
 * For example, `List<String>` matches `hasTypeOf<List<String>>()`, but not `hasTypeOf<List<Int>>()`.
 *
 * @return `true` if the declaration has a type matching [T], `false` otherwise.
 */
inline fun <reified T> KoNonNullableTypeProvider.hasTypeOf(): Boolean = hasTypeOf(typeOf<T>())
