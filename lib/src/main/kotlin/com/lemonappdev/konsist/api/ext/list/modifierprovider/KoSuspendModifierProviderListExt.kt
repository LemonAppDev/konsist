import com.lemonappdev.konsist.api.provider.modifier.KoSuspendModifierProvider

/**
 * List containing declarations with `suspend` modifier.
 *
 * @return A list containing declarations with the `suspend` modifier.
 */
fun <T : KoSuspendModifierProvider> List<T>.withSuspendModifier(): List<T> = filter { it.hasSuspendModifier }

/**
 * List containing declarations without `suspend` modifier.
 *
 * @return A list containing declarations without the `suspend` modifier.
 */
fun <T : KoSuspendModifierProvider> List<T>.withoutSuspendModifier(): List<T> = filterNot { it.hasSuspendModifier }
