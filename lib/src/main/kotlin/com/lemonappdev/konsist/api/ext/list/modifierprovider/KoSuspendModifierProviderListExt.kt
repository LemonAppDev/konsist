import com.lemonappdev.konsist.api.provider.modifier.KoSuspendModifierProvider

/**
 * List containing elements with `suspend` modifier.
 *
 * @return A list containing elements with the `suspend` modifier.
 */
fun <T : KoSuspendModifierProvider> List<T>.withSuspendModifier(): List<T> = filter { it.hasSuspendModifier }

/**
 * List containing elements without `suspend` modifier.
 *
 * @return A list containing elements without the `suspend` modifier.
 */
fun <T : KoSuspendModifierProvider> List<T>.withoutSuspendModifier(): List<T> = filterNot { it.hasSuspendModifier }
